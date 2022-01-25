package com.cisco.test.model;

import lombok.*;

@Data
@Builder
public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
}
