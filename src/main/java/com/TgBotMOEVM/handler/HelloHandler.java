package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.component.InlineKeyboardMaker;
import com.TgBotMOEVM.component.ReplyKeyboardMaker;
import com.TgBotMOEVM.constant.InlineButtonCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;


@Component
@RequiredArgsConstructor
public class HelloHandler implements Handler {

    private static final String START_TEXT = """
            Для авторизации введите страховой номер индивидуального лицевого счёта (СНИЛС):
            """;
    private final InlineKeyboardMaker inlineKeyboardMaker;
    private final ReplyKeyboardMaker replyKeyboardMaker;

    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        //User user = service.getUserOrCreateNewOne(update);

        return List.of(SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(START_TEXT)//String.format(GREETING, user.getName(), user.getRole()))
                .replyMarkup(inlineKeyboardMaker.getSNILS())
                .build());
    }




    @Override
    public BotCommand getCommandObject() {
        return InlineButtonCommand.HELLO;
    }
}
