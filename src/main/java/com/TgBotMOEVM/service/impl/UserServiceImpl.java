package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.AuthorisedUser;
import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.model.dictionary.UserRole;
import com.TgBotMOEVM.repository.ProfileRepository;
import com.TgBotMOEVM.repository.UserRepository;
import com.TgBotMOEVM.service.UserService;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    private final UserRepository repository;

    private final ProfileRepository profileRepository;
    @Transactional
    @Override
    public User create(Update update) {
        String telegramId = update.getMessage().getFrom().getId().toString();

        User user = repository.findByTelegramId(telegramId);

        if (user == null) {
            user = new User();
            user.setNickname(update.getMessage().getFrom().getFirstName());
            user.setLastName(update.getMessage().getFrom().getLastName());
            user.setTelegramId(telegramId);
            user.setRole(UserRole.STUDENT);

            repository.save(user);
        }
        return user;
    }

    @Transactional
    @Override
    public String authUser(Update update, String email) {
        String telegramId = update.getMessage().getFrom().getId().toString();

        User user = repository.findByTelegramId(telegramId);
        if (user == null) {
            user = this.create(update);
        }
        Optional<AuthorisedUser> profile = profileRepository.findByEmail(email);
            String validation = validate(email, user, profile);
            if (validation.equals("ОК")){
                AuthorisedUser authorisedUser = profile.get();
                user.setEmail(email);

                user.setSecond_name(authorisedUser.getSecondName());

                user.setFirst_name(authorisedUser.getFirstName());

                user.setMiddle_name(authorisedUser.getMiddleName());

                user.setBirthdate(authorisedUser.getBirthdate());

                repository.save(user);

                return "Привет, " + user.getFirst_name() + ' ' + user.getSecond_name() + '!';
        }
        return validation;
    }

    private String validate (String email, User user, Optional<AuthorisedUser> profile){
        Instant time;
        if (!EMAIL_PATTERN.matcher(email).matches()){
            return "Сообщение не соответствует формату электронной почты.";
        }
        if (profile.isEmpty()){
                return "Пользователь с такой эл.почтой ещё не авторизовался.";
        }
        else {
                time = profile.get().getAuthTime();
        }
        if (repository.findByEmail(email) != null){
            if (!Objects.equals(repository.findByEmail(email).getTelegramId(), user.getTelegramId())) {
                return "Электронная почта уже привязана к другому аккаунту. Если это сделано по ошибке, " +
                        "обратитесь в тех.поддержку по адресу support_bot@etu.ru";
            }
                else {
                    return "К Вашему Телеграм-аккаунту уже привязана учётная запись etu.ru. \nВаш адрес: " +
                            email +
                            "\nЕсли это не соответствует Вашим данным, обратитесь в тех.поддержку по адресу support_bot@etu.ru";
                }
            }
        else if (Duration.between(time, Instant.now()).toMinutes() >= 5) {
            return "С момента авторизации прошло более 5 минут. Войдите в систему повторно.";
        } else {
            return "ОК";
        }
    }


    @Override
    public List<User> getAllStudents() {
        return new ArrayList<>();//repository.findAllByRole(UserRole.STUDENT);
    }

    @Override
    public List<User> getAllTeachers() {
        return new ArrayList<>();//return repository.findAllByRole(UserRole.TEACHER);
    }

    @Override
    public Optional<User> getUser(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean changeUserRole(Update update, UserRole role) {
        boolean roleHasBeenChanged = false;

        String telegramId = update.getMessage().getFrom().getId().toString();

        User user = repository.findByTelegramId(telegramId);

        if (user.getRole() != role) {
            user.setRole(role);
            repository.save(user);

            roleHasBeenChanged = true;
        }

        return roleHasBeenChanged;
    }

}