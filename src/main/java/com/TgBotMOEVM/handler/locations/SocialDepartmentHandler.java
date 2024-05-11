package com.TgBotMOEVM.handler.locations;

import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.handler.Handler;
import com.TgBotMOEVM.repository.HandbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SocialDepartmentHandler implements Handler {
    private final HandbookRepository handbookRepository;

    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        List<BotApiMethod<?>> responses = new ArrayList<>();
        if (handbookRepository.findByCategory(ButtonCommand.SOCIAL_DEPARTMENT.getDescription()).isPresent()){
            responses.add(createMessage(chatId.toString(),
                    handbookRepository.findByCategory(ButtonCommand.SOCIAL_DEPARTMENT.getDescription()).get().getMessage()));
        }
        else{
            responses.add(createMessage(chatId.toString(),
                    "Не найдено в базе данных"));
        }
        return responses;
    }


    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.SOCIAL_DEPARTMENT;
    }
}
