package com.thoughtworks.exam.bff.adapter.client.examination;

import com.thoughtworks.exam.bff.adapter.client.answer_sheet.CreateAnswerSheetCommand;
import com.thoughtworks.exam.bff.adapter.client.answer_sheet.CreateAnswerSheetDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExaminationClient {
    @Value("${examService.host}")
    private String host;

    @Value("${examService.port}")
    private String port;

    private RestTemplate restTemplate;

    public ExaminationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String create(CreateExaminationCommand createExaminationCommand) {
        return restTemplate.postForObject(host + ":" + port + "/examinations",
                createExaminationCommand, ExaminationDTO.class).toString();
    }

    public CreateAnswerSheetDTO createAnswerSheet(CreateAnswerSheetCommand command, String examinationId) {
        return restTemplate.postForObject(host + ":" + port + "/examinations/" + examinationId + "/answer-sheets",
                command, CreateAnswerSheetDTO.class);
    }

}
