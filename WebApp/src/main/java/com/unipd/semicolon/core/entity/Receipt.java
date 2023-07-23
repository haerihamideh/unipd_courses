package com.unipd.semicolon.core.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import jakarta.persistence.*;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Drug> receiptDrugs;

    @ManyToMany
    private List<Material> receiptMaterials;

    @Column(name = "string_image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "total_amount")
    private Long totalAmount;

    public Receipt() {
    }

    public Receipt(
            List<Drug> receiptDrugs,
            List<Material> receiptMaterials,
            String image,
            LocalDate date,
            PaymentMethod paymentMethod,
            Long totalAmount) {
        this.receiptDrugs = receiptDrugs;
        this.receiptMaterials = receiptMaterials;
        this.image = image;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
    }

    // getters and setters for all attributes


    public Long getId() {
        return id;
    }

    public List<Drug> getReceiptDrugs() {
        return receiptDrugs;
    }

    public void setReceiptDrugs(List<Drug> receiptDrugs) {
        this.receiptDrugs = receiptDrugs;
    }

    public List<Material> getReceiptMaterials() {
        return receiptMaterials;
    }

    public void setReceiptMaterials(List<Material> receiptMaterials) {
        this.receiptMaterials = receiptMaterials;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
