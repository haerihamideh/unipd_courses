package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.PharmacyModel;
import com.unipd.semicolon.api.model.UserModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.PharmacyService;
import com.unipd.semicolon.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @PostMapping("")
    public ResponseEntity save(
            @RequestBody PharmacyModel model,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseHelper
                .response(pharmacyService.save(
                        model.getName(),
                        model.getAddress(),
                        model.getTellNumber(),
                        model.getTimeTable(),
                        model.getLogoPath(),
                        model.getStorage(),
                        model.getStaff(),
                        token));
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(
            @PathVariable("id") Long id,
            @RequestBody PharmacyModel model,
            @RequestHeader("Authorization") String token) {
        return ResponseHelper
                .response(pharmacyService.edit(
                        id,
                        model.getName(),
                        model.getAddress(),
                        model.getTellNumber(),
                        model.getTimeTable(),
                        model.getLogoPath(),
                        model.getStorage(),
                        model.getStaff(),
                        token));
    }

    @PutMapping("/add-staff/{id}")
    public ResponseEntity addStaff(
            @PathVariable("id") Long id,
            @RequestBody List<User> model,
            @RequestHeader("Authorization") String token) {

        return ResponseHelper.response(pharmacyService.addStaff(model, id, token));
    }

    @DeleteMapping("/delete-staff/{id}")
    public ResponseEntity deleteStaff(
            @RequestBody List<User> staffList,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseHelper.response(pharmacyService.deleteStaff(staffList, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(pharmacyService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseHelper
                .response(pharmacyService.delete(id, token));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseHelper.response(pharmacyService.getAll());

    }

    @RequestMapping(value = {"/{pharmacyId}"}, method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pharmacyActivation(@PathVariable("pharmacyId") Long pharmacyId,
                                             @RequestBody PharmacyModel status,
                                             @RequestHeader("Authorization") String token
    ) {
        return ResponseHelper.response(pharmacyService.activation(pharmacyId, status.getStatus(), token));
    }
}
