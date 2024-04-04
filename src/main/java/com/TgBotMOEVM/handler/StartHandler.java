package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.constant.Command;
import com.TgBotMOEVM.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartHandler implements Handler {

    private static final String GREETING = "Добро пожаловать, %s. Ваша роль: %s";


    //private final UserService service;

    @Override
    public List<SendMessage> handle(Update update) {
        return List.of(createMessage(update, String.format(GREETING, "Пользователь", "Гость")));//user.getRole())));
    }

    public SendMessage createMessage(Update update, String msg) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(msg);

        return message;
    }

    @Override
    public String getCommand() {
        return Command.START;
    }
}