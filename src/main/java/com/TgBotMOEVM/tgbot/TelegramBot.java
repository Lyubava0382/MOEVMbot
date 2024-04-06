package com.TgBotMOEVM.tgbot;

import com.TgBotMOEVM.DTO.InlineButtonDTO;
import com.TgBotMOEVM.annotation.InlineButtonType;
import com.TgBotMOEVM.config.BotConfig;
import com.TgBotMOEVM.encoder.InlineButtonDTOEncoder;
import com.TgBotMOEVM.handler.Handler;
import com.TgBotMOEVM.handler.InteractiveHandler;
import com.TgBotMOEVM.resolver.Resolver;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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

    private final Map<String, Handler> inlineButtonHandlers;

    private final Resolver resolver;

    public TelegramBot(BotConfig config, Resolver resolver, @InlineButtonType List<Handler> inlineButtonHandlers) {
        this.config = config;
        this.resolver = resolver;
        this.inlineButtonHandlers = inlineButtonHandlers
                .stream()
                .collect(Collectors.toMap((handler -> handler.getCommandObject().getCommand()), Function.identity()));
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
            // If we received a message

            String command = update.getMessage().getText();

            Handler handler = resolver.getHandler(command);

            if (handler != null) {

                if (handler instanceof InteractiveHandler interactiveHandler) {
                    // If it needs user input

                    // This sends the initial message and initialise the handler for new user
                    executeBotApiMethods(interactiveHandler.handle(update));


                } else {
                    // If chosen handler doesn't need user input

                    executeBotApiMethods(handler.handle(update));
                }

            }

        } else if (update.hasCallbackQuery()) {

            InlineButtonDTO buttonData = InlineButtonDTOEncoder.decode(update.getCallbackQuery().getData());

            Handler handler = inlineButtonHandlers.get(buttonData.getCommand());

            if (handler != null) {
                executeBotApiMethods(handler.handle(update));
            }

        }
    }

    /**
     * Sends all the messages from the list
     *
     * @param methods - list of bot api methods to execute
     */
    public void executeBotApiMethods(List<? extends BotApiMethod<?>> methods) {
        for (BotApiMethod<?> method : methods) {
            try {
                execute(method);
                log.info("Reply sent");
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * This function gets cache's element to trigger time based eviction checker
     *
     * @param id - id of needed item from the cache
     */


}