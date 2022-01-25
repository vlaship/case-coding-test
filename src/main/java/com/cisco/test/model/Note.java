package com.cisco.test.model;

import lombok.*;

@Data
@Builder
public class Note {
    private Integer noteId;
    private Integer caseId;
    private String details;
}
