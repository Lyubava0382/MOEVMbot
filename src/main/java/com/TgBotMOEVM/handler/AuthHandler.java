package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.annotation.InlineButtonType;
import com.TgBotMOEVM.constant.InlineButtonCommand;
import com.TgBotMOEVM.security.CustomAuthorizationRequestResolver;
import com.TgBotMOEVM.service.impl.TelegramService;
import com.TgBotMOEVM.service.impl.UserStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.Base64;
import java.util.Collections;
import java.util.List;


@Slf4j
@Component
@InlineButtonType
@RequiredArgsConstructor
public class AuthHandler implements Handler {

    @Autowired
    private TelegramService telegramService;

    @Autowired
    private UserStateService userStateService;
    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        if (update.hasCallbackQuery()) {
            Long telegramUserId = update.getCallbackQuery().getMessage().getChatId();

            // Формируем авторизационную ссылку с добавленными параметрами
            String authorizationUrl = "https://id.etu.ru/authorize";

            // Отправляем ссылку пользователю
            telegramService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), authorizationUrl);
            return List.of(createMessage(update.getCallbackQuery().getMessage().getChatId().toString(), authorizationUrl));
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            // Обработка обычных текстовых сообщений
            Long telegramUserId = update.getMessage().getFrom().getId();
            String authorizationUrl = "https://id.etu.ru/authorize";
            // Генерируем код вызова (code_verifier)


            // Отправляем ссылку пользователю
            telegramService.sendMessage(update.getMessage().getChatId(), authorizationUrl);
            return List.of(createMessage(update.getMessage().getChatId().toString(), authorizationUrl));
        }

        return Collections.emptyList();
    }

    public void onUpdateReceived(Update update) {

    }


    @Override
    public BotCommand getCommandObject() {
        return InlineButtonCommand.AUTH;
    }
}




/*
    @Override
    public List<BotApiMethod<?>> update(Update update) {
        String SNILS = update.getMessage().getFrom().getId().toString();
        if (SNILS == null) {
            log.error("DTO instance wasn't created yet! Cannot continue. User id: {}", update.getMessage().getFrom().getId());
            throw new IllegalStateException("There's no DTO created to receive message for parent.");
        }

        String SNILS2 = update.getMessage().getText();

        // Sending buttons with students. Data from them will be used in the next state
        SendMessage sendMessage = SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(String.format(SUCCESS_TEXT, SNILS, SNILS2))
                .build();

        return List.of(sendMessage);
    }*/

