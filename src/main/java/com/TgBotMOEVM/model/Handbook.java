package com.TgBotMOEVM.model;

import com.TgBotMOEVM.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Handbook")
@Entity
public class Handbook extends BaseEntity {

    @Column(name = "category", nullable = false, unique = true)
    private String category;

    @Column(name = "menu")
    private String menu;

    @Column(name = "message", unique = true, length = 4095)
    private String message;

}