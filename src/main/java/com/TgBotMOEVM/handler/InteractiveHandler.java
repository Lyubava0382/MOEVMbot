package com.TgBotMOEVM.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface InteractiveHandler extends Handler {
    /**
     * Continues handler's execution based on user input and current state
     * @param update Data from user
     * @return List of messages to be sent to the user
     *
     //* @throws IllegalUserInputException If user entered something wrong
     */
    List<SendMessage> update(Update update);// throws IllegalUserInputException, IllegalStateException;

    /**
     * Returns initial state for this handler
     * @return Initial state for this handler
     */

}