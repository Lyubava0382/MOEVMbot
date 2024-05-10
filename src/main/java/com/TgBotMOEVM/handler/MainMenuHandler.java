package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.component.ReplyKeyboardMaker;
import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MainMenuHandler implements Handler{
    private static final String GREETING = "В главное меню";

    private final UserService service;

    private final ReplyKeyboardMaker replyKeyboardMaker;


    @Override
    public List<BotApiMethod<?>> handle(Update update) {

        return List.of(createMessage(update.getMessage().getChatId().toString(),
                GREETING, replyKeyboardMaker.getMainMenuKeyboard()));

    }

    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.TO_MAIN_MENU;
    }
}
