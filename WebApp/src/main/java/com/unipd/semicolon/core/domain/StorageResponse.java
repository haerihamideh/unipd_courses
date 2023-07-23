package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;

public class StorageResponse {

    private Long id;
    private Pharmacy pharmacy;
    private Drug drug;

    private Material material;
    private int amount;
    private int threshold;

    private double discount;

    public StorageResponse(Long id,
                           Pharmacy pharmacy,
                           Drug drug,
                           Material material,
                           int amount,
                           int threshold,
                           double discount) {
        this.id = id;
        this.pharmacy = pharmacy;
        this.drug = drug;
        this.material = material;
        this.amount = amount;
        this.threshold = threshold;
        this.discount = discount;
    }

    public Long getId() {

        return id;
    }

    public Pharmacy getPharmacy() {

        return pharmacy;
    }

    public Drug getDrug() {

        return drug;
    }

    public Material getMaterial() {

        return material;
    }

    public int getAmount() {

        return amount;
    }

    public int getThreshold() {

        return threshold;
    }

    public double getDiscount() {

        return discount;
    }
}

