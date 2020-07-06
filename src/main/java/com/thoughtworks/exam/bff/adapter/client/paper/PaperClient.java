package com.thoughtworks.exam.bff.adapter.client.paper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaperClient {

    @Value("${examService.host}")
    private String host;

    @Value("${examService.port}")
    private String port;

    private RestTemplate restTemplate;

    public PaperClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String create(CreatePaperCommand createPaperCommand) {
        return restTemplate.postForObject(host + ":" + port + "/papers", createPaperCommand, PaperDTO.class).toString();
    }
}