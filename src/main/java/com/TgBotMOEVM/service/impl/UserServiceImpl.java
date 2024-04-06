package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.model.dictionary.UserRole;
import com.TgBotMOEVM.repository.UserRepository;
import com.TgBotMOEVM.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Transactional
    @Override
    public User create(Update update) {
        String telegramId = update.getMessage().getFrom().getId().toString();

        User user = repository.findByTelegramId(telegramId);

        if (user == null) {
            user = new User();
            user.setName(update.getMessage().getFrom().getFirstName());
            user.setLastName(update.getMessage().getFrom().getLastName());
            user.setTelegramId(telegramId);
            user.setRole(UserRole.STUDENT);

            repository.save(user);
        }
        return user;
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