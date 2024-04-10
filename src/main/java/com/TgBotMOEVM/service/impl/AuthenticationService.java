package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.Student;
import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.repository.StudentRepository;
import com.TgBotMOEVM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.TgBotMOEVM.util.UserState.AWAITING_SNILS;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final UserStateService userStateService;

    public String getStudent(String messageSnils, String telegramId) {
        if (userStateService.getUserState(Long.valueOf(telegramId)) == AWAITING_SNILS){
            String[] parts = messageSnils.split(" ");
            if (parts.length < 2)
                return "Пожалуйста, используйте формат: /snils [номер]";
            else {
                String snilsNumber = parts[1];
                if (studentRepository.findBySnils(snilsNumber).isEmpty())
                    return "Студента с таким СНИЛС ещё нет в базе.";
                else return "Номер СНИЛС успешно сохранён: " + snilsNumber;
            }
        }
        else return "От Вас не ожидается подтверждение СНИЛС. Команда недоступна.";

    }
}
