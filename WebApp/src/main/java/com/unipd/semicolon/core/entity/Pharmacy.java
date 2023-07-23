package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unipd.semicolon.core.entity.enums.PharmacyStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pharmacy")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "telephoneNumber")
    private String telephoneNumber;
    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacy")
    private List<TimeTable> time_table;

    @Column(name = "logo")
    private byte[] logo;

    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacy")
    private List<Storage> storage;

    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacy")
    private List<User> staff;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PharmacyStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacy")
    private List<Order> orders;

    public Pharmacy() {

    }

    public Pharmacy(
            String name,
            String address,
            String telephoneNumber,
            byte[] logo,
            PharmacyStatus status) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.logo = logo;
        this.status = status;
    }

    public Pharmacy(String name, String address, String telephoneNumber, byte[] logo) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.logo = logo;
        this.status = PharmacyStatus.ACTIVE;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<TimeTable> getTime_table() {
        return time_table;
    }

    public void setTime_table(List<TimeTable> time_table) {
        this.time_table = time_table;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }

    public List<User> getStaff() {
        return staff;
    }

    public void setStaff(List<User> staff) {
        this.staff = staff;
    }

    public PharmacyStatus getStatus() {
        return status;
    }

    public void setStatus(PharmacyStatus status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
