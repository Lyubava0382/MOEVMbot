package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.DTO.InlineButtonDTO;
import com.TgBotMOEVM.annotation.InlineButtonType;
import com.TgBotMOEVM.constant.InlineButtonCommand;
import com.TgBotMOEVM.encoder.InlineButtonDTOEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

@Slf4j
@Component
@InlineButtonType
@RequiredArgsConstructor
public class SNILSHandler implements Handler {

    private static final String START_TEXT = """
            Для авторизации введите страховой номер индивидуального лицевого счёта (СНИЛС):
            """;

    private static final String SUCCESS_TEXT = "Ваш снилс \"%s\" упешно установлена. \"%s\"";

    @Override
    public List<BotApiMethod<?>> handle(Update update) {

        InlineButtonDTO inlineButtonDTO = InlineButtonDTOEncoder.decode(update.getCallbackQuery().getData());

        String message0 = "";
        // Sending start message
        SendMessage message;

        message = SendMessage.builder()
                .chatId(update.getCallbackQuery().getMessage().getChatId().toString())
                .text("12345678")
                .build();

        return List.of(message, new AnswerCallbackQuery(update.getCallbackQuery().getId()));
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

    @Override
    public BotCommand getCommandObject() {
        return InlineButtonCommand.TEST_SNILS;
    }


}
