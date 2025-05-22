package com.example.demo_test_task.fakerestapitests;

import com.example.demo_test_task.BaseTest;
import com.example.demo_test_task.dto.Book;
import com.example.demo_test_task.services.it.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;

import java.util.NoSuchElementException;

import static com.example.demo_test_task.utils.FakerUtils.faker;

public class BookTests extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    void testGetAllBooksReturns200AndListOfBooks() {
        var response = bookService.getAll();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void testGetBookByIdReturnsCorrectBook() {
        var book = bookService.getAll().getBody().stream().findAny().orElseThrow(() -> new NoSuchElementException("No books"));
        var response = bookService.getById(book.getId());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(response.getBody()).isEqualTo(book);
    }

    @Test
    void testGetBookByIdNotFoundReturns404() {
        var response = bookService.getById(-1);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(404));
    }

    @Test
    void testCreateBookWithValidDataReturns200() {
        var book = Book.builder()
                .title(faker.book().title())
                .description(faker.lorem().sentence())
                .pageCount(faker.random().nextInt(1000))
                .excerpt(faker.lorem().paragraph(5))
                .publishDate("2025-05-21T22:26:14.684Z")
                .build();
        var response = bookService.create(book);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(response.getBody()).usingRecursiveComparison().ignoringFields("id").isEqualTo(book);
    }

    @Test
    void testCreateBookWithInvalidDataReturns400() {
        var book = Book.builder()
                .title(faker.book().title())
                .description(faker.lorem().sentence())
                .pageCount(faker.random().nextInt(1000))
                .excerpt(faker.lorem().paragraph(5))
                .publishDate("xxx")
                .build();
        var response = bookService.create(book);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
        ;
    }

    @Test
    void testUpdateBookWithValidDataReturns200() {
        var book = Book.builder()
                .id(3)
                .title(faker.book().title())
                .description(faker.lorem().sentence())
                .pageCount(faker.random().nextInt(1000))
                .excerpt(faker.lorem().paragraph(5))
                .publishDate("2025-05-21T22:26:14.684Z")
                .build();
        var response = bookService.update(3, book);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        ;
        Assertions.assertThat(response.getBody()).isEqualTo(book);
    }

    @Test
    void testDeleteExistingBookReturns200() {
        var response = bookService.delete(1);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        ;
    }
}
