package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.OrderModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.enums.OrderReport;
import com.unipd.semicolon.business.service.OrderService;
import com.unipd.semicolon.core.entity.enums.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Operation(summary = "Saving order",
            description = "Saving the order base the info")
    public ResponseEntity save(@RequestBody OrderModel model) {
        return ResponseHelper
                .response(orderService.save(
                        model.getToken(),
                        model.getOrderDate(),
                        model.getOrderDrugs(),
                        model.getOrderMaterials(),
                        model.getStatus(),
                        model.getPrice(),
                        model.isActive(),
                        model.getPharmacy()
                ));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Getting order base an id.",
            description = "we will send order id and token.")
    public ResponseEntity getById(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token) {
        return ResponseHelper
                .response(orderService.getById(token, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Deleting order",
            description = "we will send order id and token.")
    public ResponseEntity deleteById(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") Long id) {

        orderService.delete(token, id);
        return ResponseHelper.response(true);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(orderService.getAll());
    }

    @RequestMapping(value = "/{id}/{status}", method = RequestMethod.PATCH)
    @Operation(summary = "Changing the status of order",
            description = "we will send order order id, status(Ex: DELIVERED) and  token.")
    public ResponseEntity status(
            @PathVariable("id") Long id,
            @PathVariable("status") OrderStatus status,
            @RequestHeader("Authorization") String token) {
        return ResponseHelper
                .response(orderService.status(token, id, status));
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity report(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) OrderReport orderReport,
            @RequestParam(required = false) Short num) {
        return ResponseHelper
                .response(orderService.reportBaseDate(
                        token,
                        orderReport,
                        num));
    }


}
