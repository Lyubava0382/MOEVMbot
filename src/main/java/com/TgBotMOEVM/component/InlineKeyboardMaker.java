package com.TgBotMOEVM.component;

import com.TgBotMOEVM.service.TelegramService;
import com.TgBotMOEVM.util.InlineKeyboardBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class InlineKeyboardMaker {

    @Autowired
    private TelegramService telegramService;


    public InlineKeyboardMarkup getUser() {

        // Создание кнопки
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardBuilder builder = InlineKeyboardBuilder.instance();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Перейти на сайт");  // Текст кнопки
        button.setUrl("http://99de-94-19-48-150.ngrok-free.app/oauth2/authorization/etu");
        builder.button(button).row();
        inlineKeyboardMarkup.setKeyboard(builder.build().getKeyboard());

        return inlineKeyboardMarkup;
    }



}
