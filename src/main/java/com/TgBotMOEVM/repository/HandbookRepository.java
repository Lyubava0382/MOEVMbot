package com.TgBotMOEVM.repository;

import com.TgBotMOEVM.model.Handbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HandbookRepository extends JpaRepository<Handbook, UUID> {
    Optional<Handbook> findByCategory(String category);

}
