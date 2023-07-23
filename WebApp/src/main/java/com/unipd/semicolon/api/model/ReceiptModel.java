package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;

import java.util.Date;
import java.util.List;

public class ReceiptModel {
    private Long id;

    private List<Long> list_drug_id;
    private Long drugs_id;

    private List<Long> list_material_id;
    private Long material_id;
    private String image;
    private Date date;
    private PaymentMethod paymentMethod;

    private Long totalAmount;


    public ReceiptModel() {
    }


    public Long getTotalAmount() {
        return totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getList_drug_id() {
        return list_drug_id;
    }

    public void setList_drug_id(List<Long> list_drug_id) {
        this.list_drug_id = list_drug_id;
    }

    public Long getDrugs_id() {
        return drugs_id;
    }

    public void setDrugs_id(Long drugs_id) {
        this.drugs_id = drugs_id;
    }

    public List<Long> getList_material_id() {
        return list_material_id;
    }

    public void setList_material_id(List<Long> list_material_id) {
        this.list_material_id = list_material_id;
    }

    public Long getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Long material_id) {
        this.material_id = material_id;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
