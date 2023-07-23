package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DrugModel {

        private Long id;
        private String name;
        private Long supplier;
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
        private Country countryOfProduction;



        public DrugModel(){

        }

        public DrugModel(
                Long id,
                String name,
                Long supplier,
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
                Country countryOfProduction,
                List<Order> orders,
                List<Receipt> receipts) {

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
                this.countryOfProduction = countryOfProduction;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long Id) { this.id = id; }

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

        public boolean getIsSensitive() {
                return isSensitive;
        }

        public void setIsSensitive(boolean sensitive) {
                isSensitive = sensitive;
        }

        public boolean getNeedPrescription() {
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



}
