package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.Handbook;
import com.TgBotMOEVM.repository.HandbookRepository;
import com.TgBotMOEVM.service.HandbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class HandbookServiceImpl implements HandbookService {

    private final HandbookRepository handbookRepository;

    @Override
    public void save(Handbook handbook) {
        if (handbookRepository.findByCategory(handbook.getCategory()).isEmpty()){
            handbookRepository.save(handbook);
        }
    }
}
