package com.TgBotMOEVM.handler;

        import com.TgBotMOEVM.constant.Command;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Component;
        import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
        import org.telegram.telegrambots.meta.api.objects.Update;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

        import java.util.ArrayList;
        import java.util.List;

@Component
@RequiredArgsConstructor
public class TestInlineButtonHandler implements Handler {

    private static final String START_TEXT = "TestInlineButtonHandler";
    private static final String USER_DATA_PATTERN = "%s%s";


    @Override
    public List<SendMessage> handle(Update update) {
        SendMessage sendMessage;

            sendMessage = createMessage(update, START_TEXT);
            sendMessage.setReplyMarkup(getStudentsButtons());

        return List.of(sendMessage);
    }

    private InlineKeyboardMarkup getStudentsButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();

            // TODO: set appropriate data for callback
            button.setCallbackData(String.valueOf(i));
            button.setText(String.format(USER_DATA_PATTERN,
                    "New ",
                    "Information"
            ));

            keyboardRow.add(button);

            keyboard.add(keyboardRow);
        }


        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    @Override
    public String getCommand() {
        return Command.TEST;
    }
}