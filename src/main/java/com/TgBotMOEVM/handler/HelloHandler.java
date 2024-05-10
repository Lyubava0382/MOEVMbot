package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.component.InlineKeyboardMaker;
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
    private final InlineKeyboardMaker inlineKeyboardMaker;

    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        return List.of(SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("""
              Чтобы авторизоваться пройдите по ссылке.
              
              После согласия на использование персональных данных,
              для подтверждения отправьте адрес электронной почты,
              привязанной к личному кабинету, используя префикс 'Email'.
              Пример: 'Email examplestudent2024@stud.etu.ru'
              """)
                .replyMarkup(inlineKeyboardMaker.getUser())
                .build());
    }


    @Override
    public BotCommand getCommandObject() {
        return InlineButtonCommand.AUTH;
    }
}
