package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.AuthorisedUser;
import com.TgBotMOEVM.model.ProfileResponse;
import com.TgBotMOEVM.model.User;
import com.TgBotMOEVM.model.dictionary.UserRole;
import com.TgBotMOEVM.repository.ProfileRepository;
import com.TgBotMOEVM.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public AuthorisedUser create(ProfileResponse.Data data) {
        AuthorisedUser authorisedUser = new AuthorisedUser();
        try{
            authorisedUser.setAuthTime(Instant.now());
            authorisedUser.setEmail(data.getEmail());
            authorisedUser.setBirthdate(LocalDate.ofInstant(
                    data.getBirthdate().toInstant(), ZoneId.systemDefault()));
            authorisedUser.setFirstName(data.getFirstName());
            authorisedUser.setSecondName(data.getSecondName());
            authorisedUser.setMiddleName(data.getMiddleName());
            profileRepository.save(authorisedUser);
        }
        catch(Exception e){

        }

        return authorisedUser;
    }
}
