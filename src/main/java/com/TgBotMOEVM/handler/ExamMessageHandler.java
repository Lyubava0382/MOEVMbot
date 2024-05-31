package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.component.InlineKeyboardMaker;
import com.TgBotMOEVM.component.ReplyKeyboardMaker;
import com.TgBotMOEVM.constant.ButtonCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamMessageHandler implements Handler {
    private final InlineKeyboardMaker inlineKeyboardMaker;

    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        return List.of(SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("""
              Чтобы увидеть расписание экзаменов, отправьте номер группы,
              используя префикс 'Экзамены'.
              
              Пример: 'Экзамены 1101'
              
              Обратите внимание, что ваш аккаунт должен быть привязан
              к ETU ID. Это можно сделать в разделе 'Авторизоваться'
              """)
                .build());
    }

    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.EXAM_TIME;
    }
}
