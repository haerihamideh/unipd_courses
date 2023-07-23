package com.unipd.semicolon.business.mapper;

import com.unipd.semicolon.core.domain.UserListExampleResponse;
import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.util.Date;

public class UserMapper {
    public static UserListExampleResponse userListExampleResponse(User user) {
        String fullName = user.getName() + " " + user.getLastName();
        return new UserListExampleResponse(fullName);
    }

    public static UserResponse userResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getGender(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getRole(),
                user.getEmail(),
                user.getAccountStatus(),
                user.getProfilePicture()
        );
    }
}
