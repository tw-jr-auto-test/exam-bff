package com.thoughtworks.exam.bff.adapter.client.answer_sheet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class AnswerSheetClient {
    @Value("${examService.host}")
    private String host;

    @Value("${examService.port}")
    private String port;

    private RestTemplate restTemplate;

    public AnswerSheetClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CreateAnswerSheetDTO createAnswerSheet(CreateAnswerSheetCommand command, String examinationId) {
        return restTemplate.postForObject(host + ":" + port + "/examinations/" + examinationId + "/answer-sheets",
                command, CreateAnswerSheetDTO.class);
    }

    public SubmitAnswerSheetDTO submitAnswer(String examinationId, String answerId, SubmitAnswerSheetCommand submitAnswerSheetCommand) {
        String url = host + ":" + port + "/examinations/" + examinationId + "/answer-sheets/" + answerId;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("ContentType", Arrays.asList("application/json"));
        HttpEntity<SubmitAnswerSheetCommand> httpEntity = new HttpEntity(submitAnswerSheetCommand, headers);
        ResponseEntity<SubmitAnswerSheetDTO> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,  httpEntity, SubmitAnswerSheetDTO.class);
        return responseEntity.getBody();
    }

}
