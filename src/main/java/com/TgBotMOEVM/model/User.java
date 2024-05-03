package com.TgBotMOEVM.model;



import com.TgBotMOEVM.model.dictionary.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User extends BaseEntity {

    @Column(name = "telegram_id")//, nullable = false, unique = true)
    private String telegramId;

    @Column(name = "email")//, unique = true)
    private String email;

    @Column(name = "first_name")//, nullable = false)
    private String first_name;

    @Column(name = "second_name")//, nullable = false)
    private String second_name;

    @Column(name = "middle_name")//, nullable = false)
    private String middle_name;

    @Column(name = "birthdate")//, nullable = false)
    private Date birthdate;

    @Column(name = "notification")//, nullable = false)
    private boolean notification;

    @Column(name = "role")//, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.GUEST;

    @Column(name = "name")//, nullable = false)
    private String name;

    @Column(name = "lastname")//, nullable = false)
    private String lastName;

    @Column(name = "snils")
    private String SNILS;
}
