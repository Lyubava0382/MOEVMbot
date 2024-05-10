package com.TgBotMOEVM.service;

import com.TgBotMOEVM.model.User;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface UserService {
    User create(Update update);
    String authUser(Update update, String email);

}