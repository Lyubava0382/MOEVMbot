package com.TgBotMOEVM.constant;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface InlineButtonCommand {

    BotCommand HELLO = new BotCommand("/hello", "Привет");
    BotCommand AUTH = new BotCommand("/auth", "Перейти по ссылке");


}