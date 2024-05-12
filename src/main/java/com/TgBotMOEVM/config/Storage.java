package com.TgBotMOEVM.config;

import com.TgBotMOEVM.model.ProfileResponse;


public class Storage {
    private static volatile Storage instance;
    private String state;

    private String codeVerifier;

    private Storage() {
    }

    // Глобальный доступный метод для получения экземпляра
    public static Storage getInstance() {
        if (instance == null) {
            synchronized (Storage.class) {
                if (instance == null) {
                    instance = new Storage();
                }
            }
        }
        return instance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCodeVerifier() {
        return codeVerifier;
    }

    public void setCodeVerifier(String codeVerifier) {
        this.codeVerifier = codeVerifier;
    }

    private ProfileResponse profileResponse;

    public ProfileResponse getProfileResponse() {
        return profileResponse;
    }

    public void setProfileResponse(ProfileResponse profileResponse) {
        this.profileResponse = profileResponse;
    }


}

