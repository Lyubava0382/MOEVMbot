package com.TgBotMOEVM.service;

import com.TgBotMOEVM.model.ProfileResponse;
import com.TgBotMOEVM.model.UserInfo;

public interface ProfileService {
    UserInfo create(ProfileResponse.Data data);
}