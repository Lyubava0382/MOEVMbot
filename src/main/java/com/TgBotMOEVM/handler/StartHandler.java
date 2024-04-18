package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.component.ReplyKeyboardMaker;
import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartHandler implements Handler {

    private static final String GREETING = "Добро пожаловать, %s. Ваша роль: %s";


    private final UserService service;

    private final ReplyKeyboardMaker replyKeyboardMaker;


    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        System.out.println("ЕСТЬ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            User user = service.create(update);
            return List.of(createMessage(update.getMessage().getChatId().toString(),
                    String.format(GREETING, user.getName(), user.getRole()), replyKeyboardMaker.getMainMenuKeyboard()));

        }
else
            return List.of(createMessage(update.getMessage().getChatId().toString(),
                    "НЕТ", replyKeyboardMaker.getMainMenuKeyboard()));


    }

    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.START;
    }
}