package com.TgBotMOEVM.service;

import com.TgBotMOEVM.model.ExamInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ExamService {
    List<ExamInfo> getExams(String groupNumber) throws JsonProcessingException;
}
