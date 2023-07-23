package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.UserModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST) //@PostMapping("/add") we can write like this too.
    // Here we get the data on the body and not on the url.
    public ResponseEntity save(
            @RequestBody UserModel model,
            @RequestHeader("Authorization") String token
    ) { //always use model.
        return ResponseHelper
                .response(userService.save(
                        model.getUsername(),
                        model.getPassword(),
                        model.getName(),
                        model.getLastName(),
                        model.getGender(),
                        model.getBirthDate(),
                        model.getPhoneNumber(),
                        model.getAddress(),
                        model.getRole(),
                        model.getEmail(),
                        model.getAccountStatus(),
                        model.getProfilePicture(),
                        token));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //Here we want to update the data, so we use PUT and not POSt.
    public ResponseEntity edit(
            @PathVariable("id") Long id,
            @RequestBody UserModel model,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseHelper
                .response(userService.edit(
                                id,
                                model.getName(),
                                model.getLastName(),
                                model.getGender(),
                                model.getBirthDate(),
                                model.getPhoneNumber(),
                                model.getAddress(),
                                model.getRole(),
                                model.getEmail(),
                                model.getAccountStatus(),
                                model.getProfilePicture(),
                                token
                        )
                );
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(userService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(userService.getById(id));
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {

        return ResponseHelper.response(userService.delete(id, token));
    }

    @RequestMapping(value = "/change-status/{id}/{newStatus}", method = RequestMethod.PATCH)
    public ResponseEntity changeStatus(
            @PathVariable("id") Long id,
            @PathVariable("newStatus") String newStatus,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseHelper.response(userService.changeStatus(id, newStatus, token));
    }
}
