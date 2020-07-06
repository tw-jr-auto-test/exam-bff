package com.thoughtworks.exam.bff.adapter.client.answer_sheet;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateAnswerSheetDTO {
    private String answerSheetId;

    private String examinationId;

    private String studentId;

    private LocalDateTime startedTime;

    private int duration;
}
