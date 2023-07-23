package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacyRepository
        extends JpaRepository<Pharmacy, Long>, JpaSpecificationExecutor<Pharmacy> {

    // Find Pharmacy by id
    Optional<Pharmacy> findById(Long id);

    // Save an Pharmacy to the database
    Pharmacy save(Pharmacy pharmacy);

    Pharmacy getPharmacyByName(String name);

}
