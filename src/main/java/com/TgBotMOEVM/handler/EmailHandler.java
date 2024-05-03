package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.component.ReplyKeyboardMaker;
import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailHandler implements Handler {


    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        List<BotApiMethod<?>> responses = new ArrayList<>();

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();
            Long chatId = message.getChatId();

            // Проверяем, начинается ли сообщение с команды /email
            if (messageText.startsWith("/email")) {
                // Извлекаем аргумент после команды /email
                String emailArg = messageText.substring("/email".length()).trim();

                // Проверяем, что аргумент не пустой
                if (!emailArg.isEmpty()) {
                    // Обрабатываем полученный аргумент
                    responses.add(createMessage(chatId.toString(), "Обработка email: " + emailArg));
                } else {
                    // Если аргумент отсутствует
                    responses.add(createMessage(chatId.toString(), "Пожалуйста, введите email после команды /email."));
                }
            }
        }

        return responses;
    }



    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.EMAIL;
    }
}