package com.TgBotMOEVM.service.impl;

import com.TgBotMOEVM.model.ExamInfo;
import com.TgBotMOEVM.service.ExamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ModelMapper modelMapper;

    @Override
    public List<ExamInfo> getExams(String groupNumber) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.exchange("https://digital.etu.ru/api/mobile/exam?groupNumber="
                + groupNumber, HttpMethod.GET, entity, String.class);
        if (response.hasBody()){
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, List<ExamInfo>> lessonsMap = objectMapper.readValue(response.getBody(), new TypeReference<Map<String, List<ExamInfo>>>() {});
            return lessonsMap.get(groupNumber);
        }
        else return Collections.emptyList();
    }
}
