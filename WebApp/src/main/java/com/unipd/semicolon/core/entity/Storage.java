package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_pharmacy")
    private Pharmacy pharmacy;

    @OneToOne
    private Drug drug;

    @OneToOne
    private Material material;

    @Column(name = "amount")
    private int amount;
    @Column(name = "threshold")
    private int threshold;

    @Column(name = "discount")
    private double discount;

    public Storage() {

    }

    public Storage(
            Pharmacy pharmacy,
            Drug drug,
            Material material,
            int amount,
            int threshold,
            double discount) {
        this.pharmacy = pharmacy;
        this.drug = drug;
        this.material = material;
        this.amount = amount;
        this.threshold = threshold;
        this.discount = discount;
    }

    public Storage(Long id, Pharmacy pharmacy, Drug drug, Material material, int amount, int threshold) {
        this.id = id;
        this.pharmacy = pharmacy;
        this.drug = drug;
        this.material = material;
        this.amount = amount;
        this.threshold = threshold;
    }

    public Long getId() {
        return id;
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
}
