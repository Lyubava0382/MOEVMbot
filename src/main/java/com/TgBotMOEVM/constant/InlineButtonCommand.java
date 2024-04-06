package com.TgBotMOEVM.constant;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface InlineButtonCommand {
    //String GET_MORE_SUBSCRIPTION_INFO = "/subscriptionGetMore";
    //String GET_MORE_LESSON_INFO = "/lessonGetMore";
    BotCommand HELLO = new BotCommand("/hello", "Ввести данные");

    BotCommand TEST_SNILS = new BotCommand("/testinline", "Тут будет Snils");


}