package com.thoughtworks.exam.bff.adapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.exam.bff.adapter.client.paper.CreatePaperCommand;
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
class PaperControllerTest {
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
    public void should_create_papers_successfully() throws Exception {
        CreatePaperCommand createPaperCommand = CreatePaperCommand.builder()
                .teacherId("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040")
                .quizzes(Arrays.asList(CreatePaperCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl1").score(5).build(),
                        CreatePaperCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl2").score(10).build(),
                        CreatePaperCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl3").score(20).build(),
                        CreatePaperCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl4").score(15).build(),
                        CreatePaperCommand.Quiz.builder().id("8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl6").score(30).build()))
                .build();
        String json = objectMapper.writeValueAsString(createPaperCommand);
        ResultActions resultActions = mockMvc.perform(post("/papers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
        String responseString = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(responseString).matches("[a-zA-Z-0-9]{36}");
    }
}