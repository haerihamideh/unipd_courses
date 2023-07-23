package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.ReceiptModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.ReceiptService;
import com.unipd.semicolon.core.entity.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody ReceiptModel receipt) {
        return ResponseHelper
                .response(receiptService.save(
                        receipt.getList_drug_id(),
                        receipt.getList_material_id(),
                        receipt.getImage(),
                        receipt.getPaymentMethod(),
                        receipt.getTotalAmount()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable("id") Long id) {
        return ResponseHelper.response(receiptService.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(
            @PathVariable("id") Long id,
            @RequestBody ReceiptModel receiptModel) {
        return ResponseHelper
                .response(receiptService.edit(
                        id,
                        receiptModel.getList_drug_id(),
                        receiptModel.getList_material_id(),
                        receiptModel.getImage(),
                        receiptModel.getPaymentMethod()));

    }

    @GetMapping("/get-pharmacy")
    public ResponseEntity getAll() {
        return ResponseHelper.response(receiptService.getList());
    }
}
