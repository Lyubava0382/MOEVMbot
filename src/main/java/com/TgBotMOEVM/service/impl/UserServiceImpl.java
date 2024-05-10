package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.model.UserInfo;
import com.TgBotMOEVM.model.dictionary.UserRole;
import com.TgBotMOEVM.repository.ProfileRepository;
import com.TgBotMOEVM.repository.UserRepository;
import com.TgBotMOEVM.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;
    @Transactional
    @Override
    public User create(Update update) {
        String telegramId = update.getMessage().getFrom().getId().toString();

        Optional<User> user_optional = userRepository.findByTelegramId(telegramId);
        User user;
        if (user_optional.isEmpty()) {
            user = new User();
            user.setNickname(update.getMessage().getFrom().getFirstName());
            user.setLastName(update.getMessage().getFrom().getLastName());
            user.setTelegramId(telegramId);
            user.setRole(UserRole.STUDENT);
            userRepository.save(user);
        }
        else user = user_optional.get();
        return user;
    }

    @Transactional
    @Override
    public String authUser(Update update, String email) {
        String telegramId = update.getMessage().getFrom().getId().toString();

        Optional<User> user_optional = userRepository.findByTelegramId(telegramId);
        User user;
        if (user_optional.isEmpty()) {
            user = this.create(update);
        }
        else user = user_optional.get();
        Optional<UserInfo> profile = profileRepository.findByEmail(email);
            String validation = validate(email, user, profile);
            if (validation.equals("ОК")){
                UserInfo userInfo = profile.get();
                user.setUserinfo(userInfo);
                userRepository.save(user);

                return "Привет, " + userInfo.getFirstName() + ' ' + userInfo.getSecondName() + '!';
        }
        return validation;
    }

    private String validate (String email, User user, Optional<UserInfo> profile){
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
        if (userRepository.findByEmail(email).isPresent()){
            if (!Objects.equals(userRepository.findByEmail(email).get().getTelegramId(), user.getTelegramId())) {
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


}