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

    public InlineKeyboardMarkup getUser() {

        // Создание кнопки
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Перейти на страницу");
        button.setUrl("https://e5a5-94-19-48-150.ngrok-free.app/ltgbot/login/oauth2/auth/etu");

        // Создание строки для кнопок и добавление кнопки в строку
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(button);

        // Создание списка строк и добавление нашей строки
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(rowInline);

        // Установка клавиатуры в сообщение
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }



}
