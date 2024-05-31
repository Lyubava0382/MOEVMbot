package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.ProfileResponse;
import com.TgBotMOEVM.model.UserInfo;
import com.TgBotMOEVM.repository.ProfileRepository;
import com.TgBotMOEVM.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public UserInfo create(ProfileResponse.Data data) {
        UserInfo profile = new UserInfo();
        try{
            if (profileRepository.findByEmail(data.getEmail()).isPresent()){
                profile = profileRepository.findByEmail(data.getEmail()).get();
                profile.setAuthTime(Instant.now());
            } else{

                profile.setAuthTime(Instant.now());
                profile.setEmail(data.getEmail());
                profile.setBirthdate(LocalDate.ofInstant(
                        data.getBirthdate().toInstant(), ZoneId.systemDefault()));
                profile.setFirstName(data.getFirstName());
                profile.setSecondName(data.getSecondName());
                profile.setMiddleName(data.getMiddleName());
            }
            profileRepository.save(profile);
        }
        catch(Exception e){

        }

        return profile;
    }
}
