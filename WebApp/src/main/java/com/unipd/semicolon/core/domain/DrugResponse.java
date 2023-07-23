package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DrugResponse {

    private Long id;
    private String name;
    private Supplier supplier;
    private Date expirationDate;
    private byte[] image;
    private String shape;
    private Gender gender;
    private AgeGroup ageGroup;
    private boolean isSensitive;
    private boolean needPrescription;
    private String description;
    private int limitation;
    private float price;
    private Country countryOFProduction;
    private LocalDateTime lastModifiedDate;
    private List<Order> orders;
    private List<Receipt> receipts;

    public DrugResponse(
            Long id,
            String name,
            Supplier supplier,
            Date expirationDate,
            byte[] image,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limitation,
            float price,
            Country countryOFProduction) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.image = image;
        this.shape = shape;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.isSensitive = isSensitive;
        this.needPrescription = needPrescription;
        this.description = description;
        this.limitation = limitation;
        this.price = price;
        this.countryOFProduction = countryOFProduction;
        this.lastModifiedDate = lastModifiedDate;
        this.orders = orders;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public boolean isNeedPrescription() {
        return needPrescription;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Country getCountryOFProduction() {
        return countryOFProduction;
    }

    public void setCountryOFProduction(Country countryOFProduction) {
        this.countryOFProduction = countryOFProduction;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
