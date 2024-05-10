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
public class LocationHandler implements Handler{
    private static final String GREETING = "Места силы";

    private final UserService service;

    private final ReplyKeyboardMaker replyKeyboardMaker;


    @Override
    public List<BotApiMethod<?>> handle(Update update) {

        return List.of(createMessage(update.getMessage().getChatId().toString(),
                GREETING, replyKeyboardMaker.getPlacesKeyboard()));

    }

    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.LOCATIONS;
    }
}
