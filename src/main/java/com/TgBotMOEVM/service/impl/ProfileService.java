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

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public AuthorisedUser create(AuthorisedUser authorisedUser) {
        authorisedUser.setAuthTime(Instant.now());
        profileRepository.save(authorisedUser);
        return authorisedUser;
    }
}
