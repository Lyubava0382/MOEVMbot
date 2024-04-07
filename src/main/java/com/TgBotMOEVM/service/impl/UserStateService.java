package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.util.UserState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserStateService {
    private final Map<Long, UserState> userStates = new HashMap<>();

    public void setUserState(Long userId, UserState state) {
        userStates.put(userId, state);
    }

    public UserState getUserState(Long userId) {
        return userStates.getOrDefault(userId, UserState.IDLE);
    }
}
