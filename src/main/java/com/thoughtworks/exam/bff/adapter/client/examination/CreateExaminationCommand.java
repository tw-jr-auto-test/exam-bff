package com.thoughtworks.exam.bff.adapter.client.examination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateExaminationCommand {
    private String teacherId;
    private String paperId;
    private int duration;
    private List<Quiz> quizzes;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Quiz {
        private String id;
        private Integer score;
        private String question;
        private String referenceAnswer;
    }
}
