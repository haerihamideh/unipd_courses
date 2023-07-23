package com.unipd.semicolon.business.service;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.core.domain.DrugResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DrugService {


    Drug save(
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
            Country countryOFProduction
    ) throws CustomException;

    Drug edit(
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
            Country countryOFProduction
    ) throws SQLException;


    DrugResponse getById(Long id);


    List<DrugResponse> getAll(
            Long supplierId,
            Integer isSensitive,
            Country countryOFProduction,
            String shape,
            Gender gender) throws SQLException;

    List<Drug> findDrugsByNameAndSupplierAndExpirationDateAndShapeAndAgeGroupAndCountryOfProduction(
            String name,
            Supplier supplier,
            Date expirationDate,
            String shape,
            AgeGroup ageGroup,
            Country countryOfProduction
    );
}
