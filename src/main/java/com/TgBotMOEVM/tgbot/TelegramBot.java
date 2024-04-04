package com.TgBotMOEVM.tgbot;

import com.TgBotMOEVM.config.BotConfig;
import com.TgBotMOEVM.handler.Handler;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.stickers.AddStickerToSet;
import org.telegram.telegrambots.meta.api.methods.stickers.CreateNewStickerSet;
import org.telegram.telegrambots.meta.api.methods.stickers.SetStickerSetThumb;
import org.telegram.telegrambots.meta.api.methods.stickers.UploadStickerFile;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;

    private final Map<String, Handler> handlers;

    public TelegramBot(BotConfig config, List<Handler> handlers) {
        this.config = config;
        this.handlers = handlers
                .stream()
                .collect(Collectors.toMap(Handler::getCommand, Function.identity()));
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();

            Handler handler = handlers.get(command);

            if (handler != null) {
                sendMessages(handler.handle(update));
            }

        }
    }

    public void sendMessages(List<SendMessage> messages) {
        for (SendMessage message : messages) {
            try {
                execute(message);
                log.info("Reply sent");
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }
}