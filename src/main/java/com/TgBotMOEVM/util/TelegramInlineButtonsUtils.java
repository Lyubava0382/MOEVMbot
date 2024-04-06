package com.TgBotMOEVM.util;


import com.TgBotMOEVM.DTO.InlineButtonDTO;
import com.TgBotMOEVM.encoder.InlineButtonDTOEncoder;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@UtilityClass
public class TelegramInlineButtonsUtils {
    public InlineKeyboardButton createInlineButton(String commandName, String data, String text) {
        return InlineKeyboardButton.builder()
                .callbackData(InlineButtonDTOEncoder.encode(
                        InlineButtonDTO.builder()
                                .command(commandName)
                                .data(data)
                                .build()
                ))
                .text(text)
                .build();
    }

    public InlineKeyboardButton createInlineButton(BotCommand commandObject, String data){
        return InlineKeyboardButton.builder()
                .callbackData(InlineButtonDTOEncoder.encode(
                        InlineButtonDTO.builder()
                                .command(commandObject.getCommand())
                                .data(data)
                                .build()
                ))
                .text(commandObject.getDescription())
                .build();
    }

    public InlineKeyboardButton createInlineButtonWithDescriptionOverride(BotCommand commandObject,
                                                                          String descriptionOverride, String data) {
        return InlineKeyboardButton.builder()
                .callbackData(InlineButtonDTOEncoder.encode(
                        InlineButtonDTO.builder()
                                .command(commandObject.getCommand())
                                .data(data)
                                .build()
                ))
                .text(descriptionOverride)
                .build();
    }
}