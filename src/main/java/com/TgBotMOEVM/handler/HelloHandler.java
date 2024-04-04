package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.constant.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HelloHandler implements Handler {

    @Override
    public List<SendMessage> handle(Update update) {


            return List.of(createMessage(update, "hello!"));
        }

    @Override
    public String getCommand() {
        return Command.HELLO;
    }
}
