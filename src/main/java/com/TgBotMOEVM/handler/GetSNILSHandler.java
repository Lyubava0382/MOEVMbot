package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.annotation.InlineButtonType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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