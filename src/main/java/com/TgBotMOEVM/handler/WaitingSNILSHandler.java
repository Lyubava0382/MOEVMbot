package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.annotation.InlineButtonType;
import com.TgBotMOEVM.constant.InlineButtonCommand;
import com.TgBotMOEVM.service.impl.UserStateService;
import com.TgBotMOEVM.util.UserState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@InlineButtonType
@RequiredArgsConstructor
public class WaitingSNILSHandler implements Handler {

    @Autowired
    private UserStateService userStateService;

    private static final String SUCCESS_TEXT = "В ожидании вашего СНИЛС.";

    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        //InlineButtonDTO inlineButtonDTO = InlineButtonDTOEncoder.decode(update.getCallbackQuery().getData());

        if (update.hasCallbackQuery()) {
            /*
            String callData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            UserState state = userStateService.getUserState(chatId);
            if (state == UserState.AWAITING_SNILS) {
                String snilsInput = update.getMessage().getText();
                boolean isValid = validateSnils(snilsInput);
            }*/

            String callData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            userStateService.setUserState(chatId, UserState.AWAITING_SNILS);
            // Предполагаем, что ваша логика по обработке СНИЛСа здесь
            return List.of(createMessage(chatId.toString(), SUCCESS_TEXT));
        }
        return Collections.emptyList();
    }



    private boolean validateSnils(String snils) {
        // Логика валидации СНИЛСа
        return true; // Пример
    }

    @Override
    public BotCommand getCommandObject() {
        return InlineButtonCommand.SNILS;
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

