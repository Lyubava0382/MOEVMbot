package com.TgBotMOEVM.component;

import com.TgBotMOEVM.constant.InlineButtonCommand;
import com.TgBotMOEVM.util.InlineKeyboardBuilder;
import com.TgBotMOEVM.util.TelegramInlineButtonsUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class InlineKeyboardMaker {

    public InlineKeyboardMarkup getLessonMainMenuInlineKeyboard() {//Lesson lesson, LessonInfo lessonInfo, UserRole role) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardBuilder builder = InlineKeyboardBuilder.instance();
// if != null
        {
            builder.button(TelegramInlineButtonsUtils.createInlineButton(
                    InlineButtonCommand.TEST_SNILS,
                    "snils!"
            )).row();
        }
        inlineKeyboardMarkup.setKeyboard(builder.build().getKeyboard());

        return inlineKeyboardMarkup;
    }


}
