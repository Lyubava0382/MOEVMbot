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
@Table(name = "Users")
@Entity
public class User extends BaseEntity {

    @Column(name = "telegram_id", nullable = false, unique = true)
    private String telegramId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userinfo_id", referencedColumnName = "id", unique = true)
    private UserInfo userinfo;

    @Column(name = "notification")//, nullable = false)
    private boolean notification;

    @Column(name = "role")//, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.GUEST;

    @Column(name = "nickname")//, nullable = false)
    private String nickname;

    @Column(name = "lastname")//, nullable = false)
    private String lastName;

}
