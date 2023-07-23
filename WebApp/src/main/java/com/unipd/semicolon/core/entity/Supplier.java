package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @JsonManagedReference
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE)
    private List<Drug> drugs;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Material> materials;

    public Supplier() {
    }

    public Supplier(
            String name,
            String address,
            String email,
            String telephoneNumber,
            List<Drug> drugs,
            List<Material> materials) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.drugs = drugs;
        this.materials = materials;
    }

    public Supplier(
            String name,
            String address,
            String email,
            String telephoneNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

}

