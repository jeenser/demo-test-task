package com.example.demo_test_task.services.it;

import com.example.demo_test_task.dto.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService extends FakeRestApiService<Integer, Author, Author> {
    ResponseEntity<List<Author>> getAuthorsByBookId(Integer idBook);
}
