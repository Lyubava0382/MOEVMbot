package com.TgBotMOEVM.component;

import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.constant.InlineButtonCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ReplyKeyboardMaker {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row = new KeyboardRow();
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(InlineButtonCommand.AUTH.getDescription()))));
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.UNIVERSITY.getDescription()))));
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.LINKS.getDescription()))));
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.EXAM_TIME.getDescription()))));
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.NEXT.getDescription()))));
        keyboard.add(row);

        return getReplyKeyboardMarkup(keyboard);

    }
    public ReplyKeyboardMarkup getUniversityKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        row1.add(new KeyboardButton(ButtonCommand.RECTOR.getDescription()));
        row1.add(new KeyboardButton(ButtonCommand.DEANERY.getDescription()));

        row2.add(new KeyboardButton(ButtonCommand.MOEVM.getDescription()));
        row2.add(new KeyboardButton(ButtonCommand.DEPARTMENTS.getDescription()));

        row3.add(new KeyboardButton(ButtonCommand.UNION.getDescription()));
        row3.add(new KeyboardButton(ButtonCommand.TO_MAIN_MENU.getDescription()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        return getReplyKeyboardMarkup(keyboard);

    }

    public ReplyKeyboardMarkup getSecondPageKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();

        row1.add(new KeyboardButton(ButtonCommand.LOCATIONS.getDescription()));

        row2.add(new KeyboardButton(ButtonCommand.HEALTH.getDescription()));
        row2.add(new KeyboardButton(ButtonCommand.MILITARY.getDescription()));

        row3.add(new KeyboardButton(ButtonCommand.EDUCATIONAL.getDescription()));
        row3.add(new KeyboardButton(ButtonCommand.ACTIVITIES.getDescription()));

        row4.add(new KeyboardButton(ButtonCommand.TO_MAIN_MENU.getDescription()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        return getReplyKeyboardMarkup(keyboard);

    }
    public ReplyKeyboardMarkup getEducationKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();

        row1.add(new KeyboardButton(ButtonCommand.COURSEWORK.getDescription()));
        row1.add(new KeyboardButton(ButtonCommand.TESTS.getDescription()));

        row2.add(new KeyboardButton(ButtonCommand.TEST_WEEK.getDescription()));
        row2.add(new KeyboardButton(ButtonCommand.EXAMS.getDescription()));

        row3.add(new KeyboardButton(ButtonCommand.QUALITY_DAY.getDescription()));
        row3.add(new KeyboardButton(ButtonCommand.ADD_SESSION.getDescription()));

        row4.add(new KeyboardButton(ButtonCommand.COMMISSION.getDescription()));
        row4.add(new KeyboardButton(ButtonCommand.TO_MAIN_MENU.getDescription()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        return getReplyKeyboardMarkup(keyboard);

    }
    public ReplyKeyboardMarkup getPlacesKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();

        row1.add(new KeyboardButton(ButtonCommand.OFFICE.getDescription()));

        row2.add(new KeyboardButton(ButtonCommand.SOCIAL_DEPARTMENT.getDescription()));
        row2.add(new KeyboardButton(ButtonCommand.PAID.getDescription()));

        row3.add(new KeyboardButton(ButtonCommand.DORMITORY.getDescription()));
        row3.add(new KeyboardButton(ButtonCommand.FOREIGN_CITIZENS.getDescription()));

        row4.add(new KeyboardButton(ButtonCommand.LIBRARY.getDescription()));
        row4.add(new KeyboardButton(ButtonCommand.TO_MAIN_MENU.getDescription()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        return getReplyKeyboardMarkup(keyboard);

    }

    public ReplyKeyboardMarkup getHealthKeyboard() {
        KeyboardRow row = new KeyboardRow();
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.HEALTH_CENTER.getDescription()))));
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.CLINICS.getDescription()))));
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.FOREIGNERS.getDescription()))));
        keyboard.add(new KeyboardRow(Collections.singletonList(new KeyboardButton(ButtonCommand.TO_MAIN_MENU.getDescription()))));

        keyboard.add(row);

        return getReplyKeyboardMarkup(keyboard);

    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup(List<KeyboardRow> keyboardRows) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        return replyKeyboardMarkup;
    }
}