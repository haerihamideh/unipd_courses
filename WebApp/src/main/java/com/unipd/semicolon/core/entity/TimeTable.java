package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "TimeTable")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;
    @Column(name = "from_hour")
    private String from_hour;
    @Column(name = "to_hour")
    private String to_hour;


    public TimeTable() {
    }




    public TimeTable(Long day_of_week,
                     String from_hour,
                     String to_hour) {
        this.id = day_of_week;
        this.from_hour = from_hour;
        this.to_hour = to_hour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getFrom_hour() {
        return from_hour;
    }

    public void setFrom_hour(String from_hour) {
        this.from_hour = from_hour;
    }

    public String getTo_hour() {
        return to_hour;
    }

    public void setTo_hour(String to_hour) {
        this.to_hour = to_hour;
    }


}