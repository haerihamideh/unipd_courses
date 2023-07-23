package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Enumerated(EnumType.STRING)
    @Column(name = "country_of_production")
    private Country countryOfProduction;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "image")
    private byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    public Gender gender;

    @Column(name = "price")
    private float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group")
    private AgeGroup ageGroup;

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;


    @Column(name = "description")
    private String description;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "orderMaterials")
//    private List<Order> orders;

    @JsonIgnore
    @ManyToMany(mappedBy = "receiptMaterials")
    private List<Receipt> receipts;

    //just for check

    public Material() {

    }

    public Material(
            String name,
            Supplier supplier,
            Country countryOfProduction,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            float price,
            AgeGroup ageGroup,
            LocalDate lastModifiedDate,
            String description) {
        this.name = name;
        this.supplier = supplier;
        this.countryOfProduction = countryOfProduction;
        this.expirationDate = expirationDate;
        this.image = image;
        this.gender = gender;
        this.price = price;
        this.ageGroup = ageGroup;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
    }

    public Material(
            String name,
            Supplier supplier,
            Country countryOfProduction,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            float price,
            AgeGroup ageGroup,
            LocalDate lastModifiedDate,
            String description,
            List<Receipt> receipts) {
        this.name = name;
        this.supplier = supplier;
        this.countryOfProduction = countryOfProduction;
        this.expirationDate = expirationDate;
        this.image = image;
        this.gender = gender;
        this.price = price;
        this.ageGroup = ageGroup;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.receipts = receipts;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Country getCountryOfProduction() {
        return countryOfProduction;
    }

    public void setCountryOfProduction(Country countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
