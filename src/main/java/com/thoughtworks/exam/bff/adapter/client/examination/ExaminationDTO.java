package com.thoughtworks.exam.bff.adapter.client.examination;

import lombok.Data;

@Data
public class ExaminationDTO {
    private String examinationId;

    @Override
    public String toString() {
        return examinationId;
    }
}
