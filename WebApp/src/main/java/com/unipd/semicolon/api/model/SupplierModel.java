package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;

import java.util.List;

public class SupplierModel {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String telephoneNumber;
    private List<Drug> drugs;
    private List<Material> materials;

    public SupplierModel() {
    }

    public SupplierModel(
            Long id,
            String name,
            String address,
            String email,
            String telephoneNumber,
            List<Drug> drugs,
            List<Material> materials) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.drugs = drugs;
        this.materials = materials;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public List<Material> getMaterials() {
        return materials;
    }

}