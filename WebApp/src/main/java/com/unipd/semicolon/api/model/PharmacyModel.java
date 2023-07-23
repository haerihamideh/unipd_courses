package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.enums.PharmacyStatus;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.entity.TimeTable;
import com.unipd.semicolon.core.entity.User;

import java.util.List;

public class PharmacyModel {

    private String name;

    private String address;

    private String tell_number;
    private List<TimeTable> time_table;

    private byte[] logo;

    private List<Storage> storage;
    private List<User> staff;
    private PharmacyStatus status;

    public PharmacyModel() {
    }

    public PharmacyModel(String name,
            String address,
            String tell_number,
            List<TimeTable> time_table,
            byte[] logo,
            List<Storage> storage,
            List<User> staff,
            PharmacyStatus status) {
        this.name = name;
        this.address = address;
        this.tell_number = tell_number;
        this.time_table = time_table;
        this.logo = logo;
        this.storage = storage;
        this.staff = staff;
        this.status = status;
    }

    public PharmacyModel(PharmacyStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTellNumber() {
        return tell_number;
    }

    public List<TimeTable> getTimeTable() {
        return time_table;
    }

    public byte[] getLogoPath() {
        return logo;
    }

    public String getTell_number() {
        return tell_number;
    }

    public void setTell_number(String tell_number) {
        this.tell_number = tell_number;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTellNumber(String tell_number) {
        this.tell_number = tell_number;
    }

    public void setTimeTable(List<TimeTable> time_table) {
        this.time_table = time_table;
    }

    public void setLogoPath(byte[] logo) {
        this.logo = logo;
    }

    public void setStatus(PharmacyStatus status) {
        this.status = status;
    }

    public PharmacyStatus getStatus() {
        return status;
    }
}
