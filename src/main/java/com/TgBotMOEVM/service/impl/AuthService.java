package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.ProfileResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


public class AuthService {

    private boolean profileDone;

    private ProfileResponse profileResponse;

    public ProfileResponse getProfileResponse() {
        return profileResponse;
    }

    public void setProfileResponse(ProfileResponse profileResponse) {
        this.profileResponse = profileResponse;
    }


    public boolean isProfileDone() {
        return profileDone;
    }

    public void setProfileDone(boolean profileDone) {
        this.profileDone = profileDone;
    }
}
