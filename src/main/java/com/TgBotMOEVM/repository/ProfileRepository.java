package com.TgBotMOEVM.repository;

import com.TgBotMOEVM.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByEmail(String email);

}
