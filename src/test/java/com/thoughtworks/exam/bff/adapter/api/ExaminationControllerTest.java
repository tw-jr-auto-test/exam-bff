package com.thoughtworks.exam.bff.adapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class ExaminationControllerTest {
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
    public void should_create_examination_successfully() throws Exception {
        CreateExaminationCommand createExaminationCommand = CreateExaminationCommand.builder()
                .teacherId("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040")
                .paperId("8jk4l-k0d9ie7-4jk89l-t88irr7-h8i9040")
                .duration(120)
                .quizzes(Arrays.asList(
                        CreateExaminationCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl1").score(10)
                                .question("TDD是什么？").referenceAnswer("Test-Driven Development").build(),
                        CreateExaminationCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl2").score(20)
                                .question("DDD是什么？").referenceAnswer("Domain-Driven Design").build(),
                        CreateExaminationCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl3").score(20)
                                .question("SRP是什么？").referenceAnswer("Single Responsibility Principle").build(),
                        CreateExaminationCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl4").score(20)
                                .question("YAGNI是什么？").referenceAnswer("You aren't gonna need it").build(),
                        CreateExaminationCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl5").score(30)
                                .question("DI是什么？").referenceAnswer("Dependency Injection").build()))
                .build();
        String json = objectMapper.writeValueAsString(createExaminationCommand);
        ResultActions resultActions = mockMvc.perform(post("/examinations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
        String responseString = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(responseString).matches("[a-zA-Z-0-9]{36}");
    }
}