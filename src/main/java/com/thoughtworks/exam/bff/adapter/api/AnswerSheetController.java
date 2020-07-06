package com.thoughtworks.exam.bff.adapter.api;

import com.thoughtworks.exam.bff.adapter.client.answer_sheet.CreateAnswerSheetCommand;
import com.thoughtworks.exam.bff.adapter.client.answer_sheet.CreateAnswerSheetDTO;
import com.thoughtworks.exam.bff.adapter.client.examination.ExaminationClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examinations/{examinationId}/answer-sheets")
public class AnswerSheetController {
    private final ExaminationClient examinationClient;

    public AnswerSheetController(ExaminationClient examinationClient) {
        this.examinationClient = examinationClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAnswerSheetDTO create(@PathVariable("examinationId") String examinationId, @RequestBody CreateAnswerSheetCommand command) {
        return examinationClient.createAnswerSheet(command, examinationId);
    }
}