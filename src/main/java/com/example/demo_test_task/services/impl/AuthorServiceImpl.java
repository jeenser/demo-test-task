package com.example.demo_test_task.services.impl;


import com.example.demo_test_task.client.HttpClient;
import com.example.demo_test_task.dto.Author;
import com.example.demo_test_task.services.it.AuthorService;
import com.google.common.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo_test_task.client.SystemApi.*;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final HttpClient httpClient;

    @Override
    public ResponseEntity<List<Author>> getAll() {
        return httpClient.execute(GET_AUTHORS, new TypeToken<List<Author>>() {
        });
    }

    @Override
    public ResponseEntity<Author> create(Author body) {
        return httpClient.execute(CREATE_AUTHOR, body, Author.class);
    }

    @Override
    public ResponseEntity<Author> getById(Integer id) {
        return httpClient.execute(GET_AUTHOR_BY_ID, Author.class, id);
    }

    @Override
    public ResponseEntity<Author> update(Integer id, Author body) {
        return httpClient.execute(UPDATE_AUTHOR, body, Author.class, id);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return httpClient.execute(DELETE_AUTHOR, Void.class, id);
    }

    @Override
    public ResponseEntity<List<Author>> getAuthorsByBookId(Integer idBook) {
        return httpClient.execute(GET_AUTHOR_BOOKS, new TypeToken<List<Author>>() {
        }, idBook);
    }
}
