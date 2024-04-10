package com.TgBotMOEVM.model;

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
@Table(name = "Student")
@Entity
public class Student extends BaseEntity {

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "snils")
    private String snils;
}
