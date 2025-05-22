package com.example.demo_test_task.client;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import static org.springframework.http.HttpMethod.*;

@Getter
@AllArgsConstructor
public enum SystemApi {

    // Authors
    GET_AUTHORS(GET, "/Authors"),
    CREATE_AUTHOR(POST, "/Authors"),
    GET_AUTHOR_BY_ID(GET, "/Authors/{id}"),
    UPDATE_AUTHOR(PUT, "/Authors/{id}"),
    DELETE_AUTHOR(DELETE, "/Authors/{id}"),

    // Books
    GET_BOOKS(GET, "/Books"),
    CREATE_BOOK(POST, "/Books"),
    GET_BOOK_BY_ID(GET, "/Books/{id}"),
    UPDATE_BOOK(PUT, "/Books/{id}"),
    DELETE_BOOK(DELETE, "/Books/{id}");

    private final HttpMethod method;
    private final String endpoint;
}
