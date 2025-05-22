package com.example.demo_test_task.fakerestapitests;

import com.example.demo_test_task.BaseTest;
import com.example.demo_test_task.dto.Author;
import com.example.demo_test_task.services.it.AuthorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;

import java.util.NoSuchElementException;

import static com.example.demo_test_task.utils.FakerUtils.faker;

public class AuthorTests extends BaseTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void testGetAllAuthorsReturns200AndListOfAuthors() {
        var response = authorService.getAll();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void testGetAuthorByIdReturnsCorrectAuthor() {
        var author = authorService.getAll().getBody().stream().findAny().orElseThrow(() -> new NoSuchElementException("No books"));
        var response = authorService.getById(author.getId());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(response.getBody()).isEqualTo(author);
    }

    @Test
    void testGetAuthorByIdNotFoundReturns404() {
        var response = authorService.getById(-1);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(404));
    }

    @Test
    void testCreateAuthorWithValidDataReturns200() {
        var author = Author.builder()
                .id(0)
                .idBook(0)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
        var response = authorService.create(author);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(response.getBody()).isEqualTo(author);
    }


    @Test
    void testUpdateAuthorWithValidDataReturns200() {
        var author = Author.builder()
                .id(0)
                .idBook(0)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
        var response = authorService.update(0, author);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(response.getBody()).isEqualTo(author);
    }

    @Test
    void testDeleteExistingAuthorReturns200() {
        var response = authorService.delete(0);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    }
}
