
package com.thoughtworks.exam.bff.adapter.client.paper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreatePaperCommand {
    private String teacherId;
    private List<Quiz> quizzes;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Quiz {
        private String id;
        private Integer score;
    }
}
