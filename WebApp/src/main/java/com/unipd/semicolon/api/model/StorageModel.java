package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;

import java.util.List;

public class StorageModel {
    private Long id;
    private Pharmacy pharmacy;
    private Long pharmacyId;
    private Material material;
    private List<Long> materialId;
    private Drug drug;
    private List<Long> drugId;
    private int amount;
    private int threshold;

    private double discount;


    public StorageModel(Long pharmacyId,
                        List<Long> materialId,
                        List<Long> drugId,
                        int amount,
                        int threshold,
                        double discount)
    {
        this.pharmacyId = pharmacyId;
        this.materialId = materialId;
        this.drugId = drugId;
        this.amount = amount;
        this.threshold = threshold;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public double getDiscount() {return discount;}

    public void setDiscount(double discount) {this.discount = discount;}

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public List<Long> getMaterialId() {
        return materialId;
    }

    public void setMaterialId(List<Long> materialId) {
        this.materialId = materialId;
    }

    public List<Long> getDrugId() {
        return drugId;
    }

    public void setDrugId(List<Long> drugId) {
        this.drugId = drugId;
    }
}
