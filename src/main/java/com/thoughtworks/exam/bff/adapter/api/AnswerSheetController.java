package com.thoughtworks.exam.bff.adapter.api;

import com.thoughtworks.exam.bff.adapter.client.answer_sheet.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examinations/{examinationId}/answer-sheets")
public class AnswerSheetController {
    private final AnswerSheetClient answerSheetClient;

    public AnswerSheetController(AnswerSheetClient answerSheetClient) {
        this.answerSheetClient = answerSheetClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAnswerSheetDTO create(@PathVariable("examinationId") String examinationId, @RequestBody CreateAnswerSheetCommand command) {
        return answerSheetClient.createAnswerSheet(command, examinationId);
    }

    @PutMapping("/{answerId}")
    @ResponseStatus(HttpStatus.OK)
    public SubmitAnswerSheetDTO submitAnswer(@PathVariable("examinationId")String examinationId, @PathVariable("answerId")String answerId,
                                             @RequestBody SubmitAnswerSheetCommand submitAnswerSheetCommand){
        return answerSheetClient.submitAnswer(examinationId, answerId, submitAnswerSheetCommand);
    }
}