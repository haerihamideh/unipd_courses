package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.TimeTable;
import com.unipd.semicolon.core.entity.User;

import java.util.List;

public class PharmacyResponse {
    private Long id;
    private String name;
    private String address;
    private String telephoneNumber;
    private List<TimeTable> time_table;
    private byte[] logo;

    public PharmacyResponse() {

    }

    public PharmacyResponse(Long id,
                            String name,
                            String address,
                            String telephoneNumber,
                            List<TimeTable> time_table,
                            byte[] logo,
                            List<User> staff
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.time_table = time_table;
        this.logo = logo;
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

}
