package com.example.mainapp.repository;

import com.example.mainapp.dto.Roles;
import com.example.mainapp.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {

    Optional<Authority> findByName(Roles name);
}
