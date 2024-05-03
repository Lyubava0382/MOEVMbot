package com.TgBotMOEVM.repository;

import com.TgBotMOEVM.model.AuthorisedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<AuthorisedUser, UUID> {
    Optional<AuthorisedUser> findByEmail(String email);

}
