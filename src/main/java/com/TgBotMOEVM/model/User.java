package com.TgBotMOEVM.model;


import com.TgBotMOEVM.model.dictionary.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Person")
@Entity
public class User extends BaseEntity {

    @Column(name = "role")//, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.GUEST;

    @Column(name = "telegram_id")//, nullable = false, unique = true)
    private String telegramId;

    @Column(name = "name")//, nullable = false)
    private String name;

    @Column(name = "lastname")//, nullable = false)
    private String lastName;

    @Column(name = "middle_name")//, nullable = false)
    private String middle_name;

    @Column(name = "snils")
    private String SNILS;

}