package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.DTO.InlineButtonDTO;
import com.TgBotMOEVM.annotation.InlineButtonType;
import com.TgBotMOEVM.constant.InlineButtonCommand;
import com.TgBotMOEVM.encoder.InlineButtonDTOEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@InlineButtonType
@RequiredArgsConstructor
public class GetSNILSHandler{//} implements Handler {
   /* private static final String START_TEXT = "Введите следующим сообщением текст комментария для родителя.";
    private static final String SUCCESS_TEXT = "Комментарий успешно сохранен.";

    @Override
    public List<BotApiMethod<?>> handle(Update update) {

        InlineButtonDTO inlineButtonDTO = InlineButtonDTOEncoder.decode(update.getCallbackQuery().getData());


        SetCommentForParentDTO dto = SetCommentForParentDTO.builder()
                .lessonId(UUID.fromString(inlineButtonDTO.getData()))
                .stateMachine(stateMachine)
                .build();

        setCommentForTeacherCache.put(update.getCallbackQuery().getFrom().getId().toString(), dto);

        // Sending start message
        SendMessage message;

        message = SendMessage.builder()
                .chatId(update.getCallbackQuery().getMessage().getChatId().toString())
                .text(START_TEXT)
                .build();

        return List.of(message, new AnswerCallbackQuery(update.getCallbackQuery().getId()));
    }

    @Override
    public BotCommand getCommandObject() {
        return InlineButtonCommand.SET_COMMENT_FOR_PARENT;
    }

*/
}