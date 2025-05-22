package com.example.demo_test_task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private int id;
    private int pageCount;
    private String description;
    private String excerpt;
    private String title;
    private String publishDate;
}
