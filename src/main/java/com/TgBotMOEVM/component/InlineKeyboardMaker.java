package com.TgBotMOEVM.component;

import com.TgBotMOEVM.DTO.InlineButtonDTO;
import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.constant.InlineButtonCommand;
import com.TgBotMOEVM.encoder.InlineButtonDTOEncoder;
import com.TgBotMOEVM.util.InlineKeyboardBuilder;
import com.TgBotMOEVM.util.TelegramInlineButtonsUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboardMaker {

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



}
