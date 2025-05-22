package com.example.demo_test_task.services.impl;

import com.example.demo_test_task.client.HttpClient;
import com.example.demo_test_task.dto.Book;
import com.example.demo_test_task.services.it.BookService;
import com.google.common.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo_test_task.client.SystemApi.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final HttpClient httpClient;

    @Override
    public ResponseEntity<List<Book>> getAll() {
        return httpClient.execute(GET_BOOKS, new TypeToken<>() {
        });
    }

    @Override
    public ResponseEntity<Book> create(Book body) {
        return httpClient.execute(CREATE_BOOK, body, Book.class);
    }

    @Override
    public ResponseEntity<Book> getById(Integer id) {
        return httpClient.execute(GET_BOOK_BY_ID, Book.class, id);
    }

    @Override
    public ResponseEntity<Book> update(Integer id, Book body) {
        return httpClient.execute(UPDATE_BOOK, body, Book.class, id);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return httpClient.execute(DELETE_BOOK, Void.class, id);
    }
}
