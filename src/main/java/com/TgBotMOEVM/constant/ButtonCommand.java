package com.TgBotMOEVM.constant;


import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface ButtonCommand {

    BotCommand START = new BotCommand("/start", "Start");

    BotCommand TO_MAIN_MENU = new BotCommand("/toMainMenu", "В главное меню");
    BotCommand HELLO = new BotCommand("/hello", "Привет");

    BotCommand TEST = new BotCommand("/test", "Тест");
}
