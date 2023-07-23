package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long>, JpaSpecificationExecutor<Drug> {

    Drug save(
            Drug drug);

//    List<Drug> getAll();

    Optional<Drug> findById(Long id);

//    List<Drug> searchDrug(
//            String name,
//            Supplier supplier,
//            Date expirationDate,
//            String shape,
//            AgeGroup ageGroup,
//            Country countryOfProduction
//    );

    List<Drug> findDrugsByNameAndSupplierAndExpirationDateAndShapeAndAgeGroupAndCountryOfProduction(
            String name,
            Supplier supplier,
            Date expirationDate,
            String shape,
            AgeGroup ageGroup,
            Country countryOfProduction
    );
}
