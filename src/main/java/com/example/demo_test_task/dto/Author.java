package com.example.demo_test_task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private String firstName;
    private int id;
    private int idBook;
    private String lastName;
}
