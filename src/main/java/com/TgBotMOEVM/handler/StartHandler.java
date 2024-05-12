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
public class StartHandler implements Handler {

    private static final String GREETING = """
            Добро пожаловать, %s.
            Я - Бот МОЭВМ "ЛЭТИ", твой виртуальный ассистент :-)
            Моя миссия - помочь тебе быстро найти нужную информацию.
            
            Чтобы больше узнать о нашем университете - нажимай на интересующий тебя раздел!
            
            Не забудь, что с помощью ETU ID ты можешь привязать меня к аккаунту своего личного кабинета, 
            если ты уже студент
            """;



    private final UserService service;

    private final ReplyKeyboardMaker replyKeyboardMaker;


    @Override
    public List<BotApiMethod<?>> handle(Update update) {

            User user = service.create(update);
            return List.of(createMessage(update.getMessage().getChatId().toString(),
                    String.format(GREETING, user.getNickname()), replyKeyboardMaker.getMainMenuKeyboard()));

        }

    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.START;
    }
}