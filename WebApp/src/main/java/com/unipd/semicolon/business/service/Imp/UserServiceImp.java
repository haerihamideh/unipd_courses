package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.exception.PermissionException;
import com.unipd.semicolon.business.exception.UserExistsException;
import com.unipd.semicolon.business.exception.EntityNotFoundException;
import com.unipd.semicolon.business.mapper.UserMapper;
import com.unipd.semicolon.business.service.AccountService;
import com.unipd.semicolon.business.service.SecurityService;
import com.unipd.semicolon.business.service.UserService;

import com.unipd.semicolon.business.service.ValidationService;
import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.RoleRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ValidationService validationService;


    @Override
    public Login save(String username,
                      String password,
                      String name,
                      String lastName,
                      Gender gender,
                      LocalDateTime birthDate,
                      String phoneNumber,
                      String address,
                      Role role,
                      String email,
                      String accountStatus,
                      String profilePicture,
                      String token
    ) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            if (accountService.findByUserName(username) != null) {
                throw new UserExistsException();
            } else {

                if (validationService.validateEmail(email)
                        && validationService.validateTelephoneNumber(phoneNumber) &&
                        validationService.validateBirthDate(birthDate) &&
                        validationService.validateGender(gender)

                ) {
                    User user = new User(name,
                            lastName,
                            gender,
                            birthDate,
                            phoneNumber,
                            address,
                            role,
                            email,
                            accountStatus,
                            profilePicture,
                            null);

                    User save = userRepository.save(user);
                    Login login = accountService.save(
                            username == null ? email : username,
                            password == null ? generatePassword(8) : password,
                            save);

                    return login;
                } else {
                    return null;
                }

            }
        } else {
            throw new PermissionException(token);
        }
    }

    @Override
    public Boolean edit(Long userId,
                        String name,
                        String lastName,
                        Gender gender,
                        LocalDateTime birthDate,
                        String phoneNumber,
                        String address,
                        Role role,
                        String email,
                        String accountStatus,
                        String profilePicture,
                        String token
    ) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            if (userId != null) {
                // Retrieve the user from the database
                if (userRepository.findUserById(userId) != null) {
                    User user = userRepository.findUserById(userId);

                    // Validate inputs
                    if (name != null && !name.isBlank()) {
                        user.setName(name);
                    }

                    if (lastName != null && !lastName.isBlank()) {
                        user.setLastName(lastName);
                    }

                    if (validationService.validateGender(gender)) {
                        user.setGender(gender);
                    }

                    if (validationService.validateBirthDate(birthDate)) {
                        user.setBirthDate(birthDate);
                    }

                    if (validationService.validateTelephoneNumber(phoneNumber)) {
                        user.setPhoneNumber(phoneNumber);
                    }

                    if (address != null && !address.isBlank()) {
                        user.setAddress(address);
                    }

                    if (role != null) {
                        user.setRole(role);
                    }

                    if (validationService.validateEmail(email)) {
                        user.setEmail(email);
                    }

                    if (accountStatus != null) {
                        user.setAccountStatus(accountStatus);
                    }

                    if (profilePicture != null) {
                        user.setProfilePicture(profilePicture);
                    }


                    // Save changes to the database
                    userRepository.save(user);
                    return true;
                } else {
                    throw new EntityNotFoundException("No such user exists in the database!");
                }
            } else {
                throw new CustomException("No user id is passed!");
            }

        } else {
            throw new PermissionException(token);
        }
    }

    @Override
    public Boolean changeStatus(Long id, String newStatus, String token) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            if (userRepository.findUserById(id) != null) {
                User user = userRepository.findUserById(id);
                user.setAccountStatus(newStatus);
                userRepository.save(user);
                return true;
            } else {
                throw new EntityNotFoundException("User with the provided id does not exist in the database!");
            }
        } else {
            throw new PermissionException(token);
        }

    }

    //
    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> userList = new ArrayList<>();
        List<User> users = userRepository.getAll();
        if (users == null || users.isEmpty()) {
            throw new NotFoundException();
        }
        for (User user : users) {
            userList.add(UserMapper.userResponse(user));
        }
        return userList;
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            return UserMapper.userResponse(user);
        }
        throw new EntityNotFoundException("User not found with id:" + id);
    }

    @Override
    public boolean delete(Long id, String token) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            if (id < 0) {
                throw new IllegalArgumentException("Cannot delete null user!");
            } else {
                User user = userRepository.findUserById(id);
                if (user != null) userRepository.delete(user);
                return true;
            }
        } else {
            throw new PermissionException(token);
        }

    }

    private String generatePassword(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        return random.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
