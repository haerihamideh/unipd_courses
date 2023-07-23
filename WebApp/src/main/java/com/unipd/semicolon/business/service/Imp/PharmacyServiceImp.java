package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.*;
import com.unipd.semicolon.business.exception.IllegalArgumentException;
import com.unipd.semicolon.business.exception.IllegalStateException;
import com.unipd.semicolon.business.service.AccountService;
import com.unipd.semicolon.business.service.SecurityService;
import com.unipd.semicolon.business.service.ValidationService;
import com.unipd.semicolon.core.entity.enums.PharmacyStatus;
import com.unipd.semicolon.business.service.PharmacyService;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.repository.entity.*;
import com.unipd.semicolon.core.repository.entity.TTableRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.repository.entity.PharmacyRepository;

import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PharmacyServiceImp implements PharmacyService {
    private final TTableRepository tTableRepository;

    private final PharmacyRepository pharmacyRepository;

    private final UserRepository userRepository;

    private final SecurityService securityService;

    private final StorageRepository storageRepository;

    private final DrugRepository drugRepository;

    private final MaterialRepository materialRepository;

    private final AccountService accountService;

    private final ValidationService validationService;

    @Autowired
    public PharmacyServiceImp(PharmacyRepository pharmacyRepository,
                              UserRepository userRepository,
                              TTableRepository tTableRepository,
                              SecurityService securityService,
                              StorageRepository storageRepository,
                              DrugRepository drugRepository,
                              MaterialRepository materialRepository,
                              AccountService accountService,
                              ValidationService validationService
    ) {
        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
        this.tTableRepository = tTableRepository;
        this.securityService = securityService;
        this.storageRepository = storageRepository;
        this.drugRepository = drugRepository;
        this.materialRepository = materialRepository;
        this.accountService = accountService;
        this.validationService = validationService;
    }

    // super admin only
    @Override
    public Pharmacy save(String name,
                         String address,
                         String tell_number,
                         List<TimeTable> time_table,
                         byte[] logo_path,
                         List<Storage> storages,
                         List<User> staff,
                         String token
    ) throws CustomException {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            if (name == null)
                throw new IllegalArgumentException("Name is not specified!");
            if (time_table == null)
                throw new IllegalArgumentException("time_table can not be null! Pass an empty list instead");
            if (storages == null)
                throw new IllegalArgumentException("storage can not be null! Pass an empty list instead");
            if (staff == null)
                throw new IllegalArgumentException("staff can not be null! Pass an empty list instead");
            if (tell_number != null) {
                // Check if Phone number is valid
                if (!validationService.validateTelephoneNumber(tell_number)) {
                    throw new IllegalArgumentException("Invalid phone number!");
                }
            }


            if (pharmacyRepository.getPharmacyByName(name) == null) {
                Pharmacy pharmacy = new Pharmacy(name,
                        address,
                        tell_number,
                        logo_path,
                        PharmacyStatus.ACTIVE);

                Pharmacy save = pharmacyRepository.save(pharmacy);

                if (!time_table.isEmpty()) {
                    for (TimeTable t : time_table) {
                        if (t.getPharmacy() != null) {
                            TimeTable timeTable = new TimeTable(
                                    t.getId(),
                                    t.getFrom_hour(),
                                    t.getTo_hour());

                            tTableRepository.save(timeTable);
                        }
                    }
                }

                if (!storages.isEmpty()) {
                    for (Storage s : storages) {
                        createStorage(s, save);
                    }

                }
                if (!staff.isEmpty()) {
                    for (User user : staff) {
                        User userById = userRepository.findUserById(user.getId());
                        if (userById == null)
                            throw new IllegalArgumentException("The user with id " + user.getId() + "does not exist!");
                        userById.setPharmacy(save);
                        userRepository.save(userById);
                    }
                }
                return save;
            } else {
                throw new PharmacyExistsException();
            }
        } else {
            throw new PermissionException(token);
        }
    }

    @Override
    public Boolean edit(Long id,
                        String name,
                        String address,
                        String tell_number,
                        List<TimeTable> time_table,
                        byte[] logo,
                        List<Storage> storage,
                        List<User> staff,
                        String token) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(id);
            if (pharmacyOptional.isPresent()) {
                Pharmacy pharmacy = pharmacyOptional.get();
                if (name != null)
                    pharmacy.setName(name);
                if (address != null)
                    pharmacy.setAddress(address);
                if (tell_number != null) {
                    // Check if Phone number is valid
                    if (validationService.validateTelephoneNumber(tell_number)) {
                        pharmacy.setTelephoneNumber(tell_number);
                    } else {
                        throw new IllegalArgumentException("Invalid phone number!");
                    }
                }
                if (time_table != null) {
                    for (TimeTable entry : time_table) {
                        // Check if the time format is valid
                        String regex = "^([0-1][0-9]|[2][0-3]):([0-5][0-9])$";
                        if (!entry.getFrom_hour().matches(regex) || !entry.getTo_hour().matches(regex)) {
                            throw new IllegalArgumentException("Invalid time format for TimeTable entry: " + entry.getId());
                        }

                        // Check if from_hour is before to_hour
                        LocalTime fromTime = LocalTime.parse(entry.getFrom_hour());
                        LocalTime toTime = LocalTime.parse(entry.getTo_hour());
                        if (toTime.isBefore(fromTime)) {
                            throw new IllegalArgumentException("Invalid time table entry: " + entry.getId() + " - to_hour should be after from_hour");
                        }
                    }
                    // Set the new time table only if all entries pass the validation
                    pharmacy.setTime_table(time_table);
                }
                if (logo != null)
                    pharmacy.setLogo(logo);
                if (staff != null)
                    pharmacy.setStorage(storage);
                pharmacyRepository.save(pharmacy);
                return true;
            } else {
                throw new EntityNotFoundException("Pharmacy does not exist!");
            }
        } else {
            throw new PermissionException(token);
        }
    }


    @Override
    public Pharmacy get(Long id) {
        Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(id);
        return pharmacyOptional.orElse(null);
    }

    @Override
    public Boolean addStaff(
            List<User> staffList,
            Long pharmacyId,
            String token
    ) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                    .orElseThrow(() -> new EntityNotFoundException("Pharmacy with ID " + pharmacyId + " not found!"));

            for (User staffMember : staffList) {
                User existingStaffMember = userRepository.findUserById(staffMember.getId());
                if (existingStaffMember != null) {
                    // Update existing user's pharmacy
                    existingStaffMember.setPharmacy(pharmacy);
                    userRepository.save(existingStaffMember);
                } else {
                    User newStaffMember = new User(
                            staffMember.getName(),
                            staffMember.getLastName(),
                            staffMember.getGender(),
                            staffMember.getBirthDate(),
                            staffMember.getPhoneNumber(),
                            staffMember.getAddress(),
                            staffMember.getRole(),
                            staffMember.getEmail(),
                            staffMember.getAccountStatus(),
                            staffMember.getProfilePicture(),
                            pharmacy);
                    User save = userRepository.save(newStaffMember);
                    accountService.save(save.getEmail(), generatePassword(8), save);

                }
            }

            return true;
        } else {
            throw new PermissionException(token);
        }

    }

    @Override
    public Boolean deleteStaff(List<User> staffList, String token) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            for (User staffMember : staffList) {
                User existingStaffMember = userRepository.findUserById(staffMember.getId());
                if (existingStaffMember != null) {
                    if (existingStaffMember.getPharmacy() != null
                            && existingStaffMember.getPharmacy().getId().equals(staffMember.getPharmacy().getId())) {
                        existingStaffMember.setPharmacy(null);
                        userRepository.save(existingStaffMember);
                    } else {
                        throw new IllegalArgumentException("Pharmacy ID " + staffMember.getPharmacy().getId()
                                + " does not match with the user's pharmacy ID!");
                    }
                } else {
                    throw new IllegalArgumentException("Staff member with ID " + staffMember.getId() + " not found!");
                }
            }

            return true;
        } else {
            throw new PermissionException(token);
            }
    }

    @Override
    public Boolean delete(Long id, String token) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(id);
            if (pharmacyOptional.isPresent()) {
                // Remove rows from other tables referencing this Pharmacy
                userRepository.deleteByPharmacyId(id);
                tTableRepository.deleteByPharmacyId(id);
                List<Storage> st = storageRepository.findStoragesByPharmacyId(id);
                if (!st.isEmpty()) {
                    for (Storage s : st) {
                        storageRepository.delete(s);
                    }
                }


                // Remove the Pharmacy object
                pharmacyRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } else {
            throw new PermissionException(token);
        }
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Boolean addStorageToPharmacy(Storage storage, Long pharmacyId) {
        // TODO: New feature to be implemented
        // Storage storageItem = storageRepository.findStorageByPharmacyId(s.getId());
        // if(storageItem == null) {
        // // ID is invalid, create a new Storage item for this Pharmacy
        // storageItem = new Storage(save.getId(), save, null, null, 0, 0);
        // storageRepository.save(storageItem);
        // }

        return true;
    }

    @Override
    public Pharmacy activation(
            Long pharmacyId,
            PharmacyStatus status,
            String token
    ) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                    .orElseThrow(() -> new IllegalStateException("Pharmacy not found - " + pharmacyId));

            if (status != null && !Objects.equals(status, pharmacy.getStatus())) {
                pharmacy.setStatus(status);
                if (pharmacy.getStaff().toArray().length > 0) {
                    for (User user : pharmacy.getStaff()) {
                        user.setAccountStatus(status.toString());
                        userRepository.save(user);
                    }
                }
                pharmacy = pharmacyRepository.save(pharmacy);
            } else {
                throw new InvalidParameterException();
            }

            return pharmacy;
        } else {
            throw new PermissionException(token);
        }
    }


    //Utility functions needed for this service
    private Boolean createStorage(Storage storage, Pharmacy pharmacy) {
        if (storage.getDrug() != null) {
            // check for validity of the drug
            Drug drug = drugRepository.findById(storage.getDrug().getId()).get();
            if (drug == null) {
                throw new IllegalArgumentException("Drug with id " + storage.getDrug().getId() + " does not exist!");
            } else {
                Storage newStorage = new Storage(
                        pharmacy,
                        storage.getDrug(),
                        null,
                        storage.getAmount(),
                        storage.getThreshold(),
                        1);

                storageRepository.save(newStorage);
            }

        } else if (storage.getMaterial() != null) {
            // similar check for material
            Material material = materialRepository.findById(storage.getMaterial().getId()).get();
            if (material == null) {
                throw new IllegalArgumentException("Material with id " + storage.getMaterial().getId() + " does not exist!");
            } else {
                Storage newStorage = new Storage(
                        pharmacy,
                        null,
                        storage.getMaterial(),
                        storage.getAmount(),
                        storage.getThreshold(),
                        1);
                storageRepository.save(newStorage);
            }
        }


        return true;
    }

    private String generatePassword(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        return random.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}