package com.TgBotMOEVM.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ProfileResponse {
    //private Data data;

    private AuthorisedUser data;

    public AuthorisedUser getData() {
        return data;
    }

    public void setData(AuthorisedUser data) {
        this.data = data;
    }


}