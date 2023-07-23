package com.unipd.semicolon.business.mapper;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.domain.DrugResponse;

public class DrugMapper {
    public static DrugResponse drugResponse(Drug drug){
        return new DrugResponse(
                drug.getId(),
                drug.getName(),
                drug.getSupplier(),
                drug.getExpirationDate(),
                drug.getImage(),
                drug.getShape(),
                drug.getGender(),
                drug.getAgeGroup(),
                drug.isSensitive(),
                drug.isNeedPrescription(),
                drug.getDescription(),
                drug.getLimitation(),
                drug.getPrice(),
                drug.getCountryOfProduction()
        );
    }
}
