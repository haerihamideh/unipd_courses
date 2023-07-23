package com.unipd.semicolon.core.domain;

public class StorageReportResponse {

    private Long pharmacyId;
    private int drugCount;
    private int materialCount;

    private float drugPrice;
    private float materialPrice;

    public StorageReportResponse() {
    }

    public StorageReportResponse(Long pharmacyId, int drugCount, int materialCount, float drugPrice, float materialPrice) {
        this.pharmacyId = pharmacyId;
        this.drugCount = drugCount;
        this.materialCount = materialCount;
        this.drugPrice = drugPrice;
        this.materialPrice = materialPrice;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public int getDrugCount() {
        return drugCount;
    }

    public int getMaterialCount() {
        return materialCount;
    }

    public float getDrugPrice() {
        return drugPrice;
    }

    public float getMaterialPrice() {
        return materialPrice;
    }

    @Override
    public String toString() {
        return "Pharmacy id: " + pharmacyId + " : { "
                + "Number of drugs: " + drugCount + " and Drug price: " + String.format("%.2f", drugPrice) + " € -- "
                + "Number of materials: " + materialCount + " and Material price: " + String.format("%.2f", materialPrice) + " € } ";

    }
}
