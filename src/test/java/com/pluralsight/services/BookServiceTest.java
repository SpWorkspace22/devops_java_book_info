package com.pluralsight.services;

import com.pluralsight.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class BookServiceTest {

    @ParameterizedTest
    @CsvSource({"1,Java", "2,Java ref"})
    public void testfetchRowIfPresentt(String input, String expected) {
        BookService service = new BookService();
        Optional<Book> book = service.findById(Integer.parseInt(input));
        assertEquals(book.get().getName(),expected);
    }

    @Test
    void testFetchAllBooks() {
        BookService bookService = new BookService();
        List<Book> books = bookService.getAll();
        assertTrue(!books.isEmpty());
    }


    @ParameterizedTest
    @ValueSource(strings = {"Dbms","Cpp"})
    void testSaveBookAndReturn(String name) {
        Book book = new Book();
        book.setName(name);
        BookService service = new BookService();
        Book newBook = service.save(book);
        assertNotNull(newBook.getId());
    }
}