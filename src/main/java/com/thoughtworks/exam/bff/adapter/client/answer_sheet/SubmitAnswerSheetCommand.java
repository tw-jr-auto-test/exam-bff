package com.thoughtworks.exam.bff.adapter.client.answer_sheet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SubmitAnswerSheetCommand {
    private String studentId;
    private String answer;
    private LocalDateTime startedTime;
    private LocalDateTime submittedTime;
}
