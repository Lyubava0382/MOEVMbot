package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.component.ReplyKeyboardMaker;
import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.model.User;
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

    private final UserService userService;
    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        List<BotApiMethod<?>> responses = new ArrayList<>();

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();
            Long chatId = message.getChatId();

                String emailArg = messageText.substring("Email".length()).trim();

                if (!emailArg.isEmpty()) {


                    responses.add(createMessage(chatId.toString(), "Обработка email: " + emailArg));
                    User user = userService.authUser(update, emailArg);
                    if (user != null){
                        responses.add(createMessage(chatId.toString(), "Привет, " +
                                user.getFirst_name() + ' ' + user.getSecond_name()));
                    }
                    else {
                        responses.add(createMessage(chatId.toString(), "Неудача"));

                    }
                } else {
                    responses.add(createMessage(chatId.toString(), "Пожалуйста, введите адрес своей " +
                            "электронной почты после команды Email."));

            }
        }

        return responses;
    }



    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.EMAIL;
    }
}