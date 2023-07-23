package com.unipd.semicolon.business.service;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.enums.PharmacyStatus;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.entity.TimeTable;
import com.unipd.semicolon.core.entity.User;

import java.util.List;

public interface PharmacyService {

    Pharmacy save(
            String name,
            String address,
            String tell_number,
            List<TimeTable> time_table,
            byte[] logo,
            List<Storage> storage,
            List<User> staff,
            String token) throws CustomException;

    Boolean edit(
            Long id,
            String name,
            String address,
            String tell_number,
            List<TimeTable> time_table,
            byte[] logo,
            List<Storage> storage,
            List<User> staff,
            String token

    );

    Pharmacy get(Long id);

    // Add staff
    Boolean addStaff(
            List<User> user_list,
            Long id,
            String token);

    // Delete staff
    Boolean deleteStaff(
            List<User> user_list,
            String token);

    Boolean delete(Long id, String token);

    List<Pharmacy> getAll();

    Pharmacy activation(Long id, PharmacyStatus status, String token);

    // Not sure if the list type should be user or userResponse

    Boolean addStorageToPharmacy(Storage storage, Long pharmacyId);

}
