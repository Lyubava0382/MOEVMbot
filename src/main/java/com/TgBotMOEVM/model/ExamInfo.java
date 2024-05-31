package com.TgBotMOEVM.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamInfo {
    @JsonProperty("teacher")
    private String teacher;

    @JsonProperty("secondTeacher")
    private String secondTeacher;

    @JsonProperty("name")
    private String name;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("date")
    private String date;

    @JsonProperty("room")
    private String room;
}
