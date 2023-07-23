package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.MaterialModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.MaterialService;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/material", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody MaterialModel model) throws SQLException {
        return ResponseHelper
                .response(materialService.save(
                        model.getName(),
                        model.getSupplier(),
                        model.getCountryOfProduction(),
                        model.getExpirationDate(),
                        model.getImage(),
                        model.getGender(),
                        model.getPrice(),
                        model.getAgeGroup(),
                        model.getLastModifiedDate(),
                        model.getDescription()
                ));
    }

    @RequestMapping(value = "/material/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(materialService.getById(id));
    }

    @RequestMapping(value = "/material/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id,
                               @RequestBody MaterialModel model) throws SQLException {

        return ResponseHelper
                .response(materialService.edit(
                        id,
                        model.getName(),
                        model.getSupplier(),
                        model.getExpirationDate(),
                        model.getImage(),
                        model.getGender(),
                        model.getAgeGroup(),
                        model.getPrice(),
                        model.getLastModifiedDate(),
                        model.getDescription(),
                        model.getCountryOfProduction()
                ));

    }

    @GetMapping("/material")
    public ResponseEntity getAll(
            @RequestParam(required = false) Country countryOfProduction,
            @RequestParam(required = false) Long supplierId,
            @RequestParam(required = false) Gender gender
    ) {
        return ResponseHelper
                .response(materialService.getAll(countryOfProduction, supplierId, gender));
    }
}