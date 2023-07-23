package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.*;
import com.unipd.semicolon.business.mapper.AccountMapper;
import com.unipd.semicolon.business.service.*;
import com.unipd.semicolon.core.domain.AccountResponse;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.repository.entity.LoginRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private StringService stringService;

    @Autowired
    private LocalTimeService localTimeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogSystem logSystem;

    private CustomException e;

    @Override
    public AccountResponse Login(
            String username,
            String password) throws CustomException {
        Login login = loginRepository.findByUsername(username);
        if (password.equals(stringService.decodeBase64(login.getPassword()))) {
            login.setToken(securityService.createToken(
                    login.getId().toString(),
                    login.getUser().getRole().getRole()));
            login.setLastLoginDate(localTimeService.getLocalDateTime());
        } else {
            e = new UserNameOrPasswordNotExistsException(username, password);
            logSystem.logUtil(e.getMsg());
            throw e;
        }
        Login save = loginRepository.save(login);
        return AccountMapper.loginMapper(save);
    }

    @Override
    public Boolean LogOut(String token)
            throws CustomException {
        Long id = Optional.ofNullable(securityService.getAccountId(token))
                .map(Long::parseLong)
                .orElseThrow(() -> {
                    e = new InvalidTokenException(token);
                    logSystem.logUtil(e.getMsg());
                    return e;
                });
        if (id != null && id != 0) {
            Optional<Login> login = loginRepository.findById(id);
            if (login.isPresent()) {
                login.get().setToken(null);
                loginRepository.save(login.get());
                return true;
            } else {
                e = new UserExistsException();
                logSystem.logUtil(e.getMsg());
                throw e;
            }
        } else {
            e = new InvalidTokenException(token);
            logSystem.logUtil(e.getMsg());
            throw e;
        }
    }

    // only inside of project must be call and no api have right to call this.
    @Override
    public Login save(
            String username,
            String password,
            User user) {

        String passwordEncoder = stringService.encodeBase64(password);

        Login login = new Login(
                username,
                passwordEncoder,
                null,
                null,
                localTimeService.getLocalDateTime(),
                null,
                user
        );

        return loginRepository.save(login);
    }

    @Override
    public Login findByUserName(String username) {
        return loginRepository.findByUsername(username);
    }
}
