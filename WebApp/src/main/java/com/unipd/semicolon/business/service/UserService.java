package com.unipd.semicolon.business.service;


import com.unipd.semicolon.core.domain.UserListExampleResponse;
import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.entity.enums.Gender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface UserService {

    //TODO: phone number is Long in Entity part => should change to String
    Login save(
            String username,
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
    );

    //For password retrieval we need to

    Boolean edit(Long userId,
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
    );


    //Id => userId
    Boolean changeStatus(
            Long id,
            String newStatus,
            String token
    );

    List<UserResponse> getAll();


    //TODO: User and Login entities must be connected
    //List<UserResponse> findByUserName(User userName);

    UserResponse getById(Long id);

    boolean delete(Long user, String token);
}
