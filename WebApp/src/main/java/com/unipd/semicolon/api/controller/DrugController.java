package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.DrugModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.DrugService;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody DrugModel model) {
        return ResponseHelper.response(drugService.save(
                model.getName(),
                model.getSupplier(),
                model.getExpirationDate(),
                model.getImage(),
                model.getShape(),
                model.getGender(),
                model.getAgeGroup(),
                model.getIsSensitive(),
                model.getNeedPrescription(),
                model.getDescription(),
                model.getLimitation(),
                model.getPrice(),
                model.getCountryOfProduction()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper.response(drugService.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(
            @PathVariable("id") Long id,
            @RequestBody DrugModel model) throws SQLException {

        return ResponseHelper.response(drugService.edit(
                id,
                model.getName(),
                model.getSupplier(),
                model.getExpirationDate(),
                model.getImage(),
                model.getShape(),
                model.getGender(),
                model.getAgeGroup(),
                model.getIsSensitive(),
                model.getNeedPrescription(),
                model.getDescription(),
                model.getLimitation(),
                model.getPrice(),
                model.getCountryOfProduction()));

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getAll(
            @RequestParam(required = false) Country countryOfProduction,
            @RequestParam(required = false) Long supplierId,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) Integer isSensitive,
            @RequestParam(required = false) String shape) throws SQLException {
        return ResponseHelper.response(
                drugService.getAll(
                        supplierId,
                        isSensitive,
                        countryOfProduction,
                        shape,
                        gender));
    }
}
