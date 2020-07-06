package com.thoughtworks.exam.bff.adapter.api;

import com.thoughtworks.exam.bff.adapter.client.examination.CreateExaminationCommand;
import com.thoughtworks.exam.bff.adapter.client.examination.ExaminationClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examinations")
public class ExaminationController {
    private final ExaminationClient examinationClient;


    public ExaminationController(ExaminationClient examinationClient) {
        this.examinationClient = examinationClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateExaminationCommand command) {
        return examinationClient.create(command);
    }

}