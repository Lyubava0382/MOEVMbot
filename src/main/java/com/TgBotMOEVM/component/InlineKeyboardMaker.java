package com.TgBotMOEVM.component;

import com.TgBotMOEVM.constant.InlineButtonCommand;
import com.TgBotMOEVM.service.impl.TelegramService;
import com.TgBotMOEVM.util.InlineKeyboardBuilder;
import com.TgBotMOEVM.util.TelegramInlineButtonsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class InlineKeyboardMaker {

    @Autowired
    private TelegramService telegramService;

    public InlineKeyboardMarkup getSNILS() {
        // Создаем новую inline клавиатуру
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardBuilder builder = InlineKeyboardBuilder.instance();
        builder.button(TelegramInlineButtonsUtils.createInlineButton(
                InlineButtonCommand.SNILS,
                "/snils"
        )).row();
        inlineKeyboardMarkup.setKeyboard(builder.build().getKeyboard());

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getUser() {

        // Создание кнопки
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardBuilder builder = InlineKeyboardBuilder.instance();
        builder.button(TelegramInlineButtonsUtils.createInlineButton(
                InlineButtonCommand.AUTH,
                "/auth"
        )).row();
        inlineKeyboardMarkup.setKeyboard(builder.build().getKeyboard());

        return inlineKeyboardMarkup;
    }



}
