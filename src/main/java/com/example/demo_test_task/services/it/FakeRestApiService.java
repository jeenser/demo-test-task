package com.example.demo_test_task.services.it;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FakeRestApiService<ID, T, S> {
    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> create(S body);

    ResponseEntity<T> getById(ID id);

    ResponseEntity<T> update(ID id, S body);

    ResponseEntity<Void> delete(ID id);
}
