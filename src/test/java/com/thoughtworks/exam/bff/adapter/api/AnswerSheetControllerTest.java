package com.thoughtworks.exam.bff.adapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.exam.bff.adapter.client.answer_sheet.CreateAnswerSheetDTO;
import com.thoughtworks.exam.bff.adapter.client.examination.CreateExaminationCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner
@AutoConfigureMockMvc
class AnswerSheetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void should_create_answer_sheet_successfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"studentId\":\"8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040\"}"))
                .andExpect(status().isCreated());

        String responseString = resultActions.andReturn().getResponse().getContentAsString();

        CreateAnswerSheetDTO response = objectMapper.readValue(responseString, CreateAnswerSheetDTO.class);
        assertThat(response.getAnswerSheetId()).matches("[a-zA-Z-0-9]{36}");
    }
}