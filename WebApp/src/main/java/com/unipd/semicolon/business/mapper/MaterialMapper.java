package com.unipd.semicolon.business.mapper;

import com.unipd.semicolon.core.domain.MaterialResponse;
import com.unipd.semicolon.core.entity.Material;

public class MaterialMapper {
    public static MaterialResponse materialResponse(Material material) {
        return new MaterialResponse(
                material.getId(),
                material.getName(),
                material.getSupplier(),
                material.getCountryOfProduction(),
                material.getExpirationDate(),
                material.getImage(),
                material.getGender(),
                material.getPrice(),
                material.getAgeGroup(),
                material.getLastModifiedDate(),
                material.getDescription()
        );
    }
}