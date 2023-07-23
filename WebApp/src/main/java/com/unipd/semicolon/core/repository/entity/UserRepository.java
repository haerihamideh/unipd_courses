package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.domain.UserResponse;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User save(User user);

    User findUserById(Long id);

    Boolean deleteByPharmacyId(Long id);

    List<User> getAll();

    List<User> findAllByLastName(String lastName);

    List<User> findByRoles(Role role);

    boolean delete(User user);
}
