package com.example.mainapp.repository;

import com.example.mainapp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findUserInfoByUsername(String username);

    Boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
