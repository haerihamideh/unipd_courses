package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LoginRepository extends JpaRepository<Login, Long>, JpaSpecificationExecutor<Login> {

    Login findByUsername(String username);

    Optional<Login> findById(Long id);


}
