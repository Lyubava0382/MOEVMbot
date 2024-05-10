package com.TgBotMOEVM.repository;

import com.TgBotMOEVM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByTelegramId(String telegramId);
    default Optional<User> findByEmail(String email){
        List<User> users = this.findAll();
        for (User user : users ){
            if (user.getUserinfo() != null &&
                    Objects.equals(user.getUserinfo().getEmail(), email)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
