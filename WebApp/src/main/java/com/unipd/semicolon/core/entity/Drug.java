package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "shape")
    private String shape;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group")
    private AgeGroup ageGroup;
    @Column(name = "is_sensitive")
    private boolean isSensitive;
    @Column(name = "need_prescription")
    private boolean needPrescription;
    @Column(name = "description")
    private String description;

    // num of drugs that patient can buy
    @Column(name = "limitation")
    private int limitation;
    @Column(name = "price")
    private float price;
    // Why not @Enumerated(EnumType.STRING)??
    @Enumerated(EnumType.STRING)
    @Column(name = "country_of_production")
    private Country countryOfProduction;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "drug")
    private List<OrderProduct> orders;

    @JsonIgnore
    @ManyToMany(mappedBy = "receiptDrugs")
    private List<Receipt> receipts;

    public Drug() {

    }

    public Drug(String name,
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
            Country countryOfProduction) {
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
        this.countryOfProduction = countryOfProduction;
    }

    public Drug(
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
            Country countryOFProduction,
            Date lastModifiedDate,
            List<Order> orders, List<Receipt> receipts) {
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
        this.countryOfProduction = countryOfProduction;
        this.lastModifiedDate = lastModifiedDate;
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

    public Country getCountryOfProduction() {
        return countryOfProduction;
    }

    public void setCountryOfProduction(Country countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public List<OrderProduct> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderProduct> orders) {
        this.orders = orders;
    }
}
