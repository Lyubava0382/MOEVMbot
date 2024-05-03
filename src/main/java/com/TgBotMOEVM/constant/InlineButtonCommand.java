package com.TgBotMOEVM.constant;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface InlineButtonCommand {
    //String GET_MORE_SUBSCRIPTION_INFO = "/subscriptionGetMore";
    //String GET_MORE_LESSON_INFO = "/lessonGetMore";
    BotCommand HELLO = new BotCommand("/hello", "Привет");
    BotCommand SNILS = new BotCommand("/waiting_snils", "Нажмите и введите номер после /snils");
    BotCommand USER = new BotCommand("/user", "Нажми на ссылку");
    BotCommand AUTH = new BotCommand("/auth", "Auth");


}