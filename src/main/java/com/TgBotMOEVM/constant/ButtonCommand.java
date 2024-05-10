package com.TgBotMOEVM.constant;


import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface ButtonCommand {

    BotCommand START = new BotCommand("/start", "Start");

    BotCommand TO_MAIN_MENU = new BotCommand("/toMainMenu", "В главное меню");

    BotCommand EMAIL = new BotCommand("/email", "Электронная почта");


}
