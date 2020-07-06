package com.thoughtworks.exam.bff.adapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.exam.bff.adapter.client.answer_sheet.CreateAnswerSheetDTO;
import com.thoughtworks.exam.bff.adapter.client.answer_sheet.SubmitAnswerSheetCommand;
import com.thoughtworks.exam.bff.adapter.client.answer_sheet.SubmitAnswerSheetDTO;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Test
    public void should_submit_answer_sheet_successfully() throws Exception {
        SubmitAnswerSheetCommand submitAnswerSheetCommand = SubmitAnswerSheetCommand.builder()
                .studentId("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040")
                .answer("a,b,c")
                .startedTime(LocalDateTime.parse("2020-06-27T09:00:00"))
                .submittedTime(LocalDateTime.parse("2020-06-27T10:30:00"))
                .build();
        ResultActions resultActions = mockMvc.perform(put("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheets/9idk4-lokfu-jr874j3-u8d9j4-hor82kd77")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(submitAnswerSheetCommand)))
                .andExpect(status().isOk());

        String responseString = resultActions.andReturn().getResponse().getContentAsString();

        SubmitAnswerSheetDTO response = objectMapper.readValue(responseString, SubmitAnswerSheetDTO.class);
        assertThat(response.getAnswer()).isEqualTo("a,b,c");
    }

    @Test
    public void should_submit_answer_sheet_failed_after_expired() throws Exception {
        SubmitAnswerSheetCommand submitAnswerSheetCommand = SubmitAnswerSheetCommand.builder()
                .studentId("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9041")
                .answer("a,b,c")
                .startedTime(LocalDateTime.parse("2020-06-27T09:00:00"))
                .submittedTime(LocalDateTime.parse("2020-06-27T12:30:00"))
                .build();
        mockMvc.perform(put("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheets/9idk4-lokfu-jr874j3-h8d9j4-ho7kdl8jh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(submitAnswerSheetCommand)))
                .andExpect(status().isBadRequest());
    }


}