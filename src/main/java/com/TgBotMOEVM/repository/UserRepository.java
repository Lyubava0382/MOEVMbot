package com.TgBotMOEVM.repository;

import com.TgBotMOEVM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    //User findByTelegramId(String telegramId);
}
