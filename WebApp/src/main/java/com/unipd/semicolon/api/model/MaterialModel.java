package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MaterialModel {
    private Long id;
    private String name;
    private Long supplier;
    private Country countryOfProduction;
    private LocalDate expirationDate;
    private byte[] image;
    private Gender gender;
    private float price;
    private AgeGroup ageGroup;
    private LocalDate lastModifiedDate;
    private String description;

    private List<Order> orders;

    private List<Receipt> receipts;

    public MaterialModel(
            String name,
            Long supplier,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            float price,
            AgeGroup ageGroup,
            LocalDate lastModifiedDate,
            String description,
            Country countryOfProduction,
            List<Order> orders,
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
        this.orders = orders;
        this.receipts = receipts;
    }
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}