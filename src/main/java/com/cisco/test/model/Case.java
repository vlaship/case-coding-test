package com.cisco.test.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Case {
    private Integer caseId;
    private String title;
    private String description;
    private Integer severity;
    private Status status;
    private User user;
    private List<Note> notes;

    public enum Status {
        OPEN,
        CLOSED
    }
}
