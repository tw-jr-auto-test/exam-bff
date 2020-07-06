package com.thoughtworks.exam.bff.adapter.api;

import com.thoughtworks.exam.bff.adapter.client.paper.CreatePaperCommand;
import com.thoughtworks.exam.bff.adapter.client.paper.PaperClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/papers")
public class PaperController {
    private final PaperClient paperClient;


    public PaperController(PaperClient paperClient) {
        this.paperClient = paperClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreatePaperCommand command) {
        return paperClient.create(command);
    }
}