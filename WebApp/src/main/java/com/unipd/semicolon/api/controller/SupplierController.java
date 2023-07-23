package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.SupplierModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(supplierService.findBySupplierId(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity add(
            @RequestBody SupplierModel model,
            @RequestHeader("Authorization") String token
    ) throws SQLException {
        return ResponseHelper
                .response(supplierService.save(
                        model.getName(),
                        model.getAddress(),
                        model.getEmail(),
                        model.getTelephoneNumber(),
                        model.getDrugs(),
                        model.getMaterials(),
                        token));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(
            @PathVariable("id") Long id,
            @RequestBody SupplierModel model,
            @RequestHeader("Authorization") String token
    ) throws SQLException {
        return ResponseHelper
                .response(supplierService.edit(
                        id,
                        model.getName(),
                        model.getAddress(),
                        model.getEmail(),
                        model.getTelephoneNumber(),
                        model.getDrugs(),
                        model.getMaterials(),
                        token));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity remove(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseHelper
                .response(supplierService.remove(id, token));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity getAll() throws SQLException {
        return ResponseHelper
                .response(supplierService.getSupplierList());
    }

}