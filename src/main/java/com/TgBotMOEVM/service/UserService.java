package com.TgBotMOEVM.service;

import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.model.dictionary.UserRole;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {
    User create(Update update);
    String authUser(Update update, String email);

    List<User> getAllStudents();

    List<User> getAllTeachers();

    Optional<User> getUser(UUID id);

    boolean changeUserRole(Update update, UserRole role);
}