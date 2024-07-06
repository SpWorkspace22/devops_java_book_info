package com.pluralsight;

import com.pluralsight.domain.Book;
import com.pluralsight.services.BookService;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programme Started ....");

        BookService bookService = new BookService();
        System.out.println("Connected Established");

        // Add New Book
        Book newBook = new Book();
        newBook.setName("Java ref");
        newBook = bookService.save(newBook);
        System.out.println("Book Saved "+newBook);

        //Get All Books
        List<Book> books = bookService.getAll();
        System.out.println("Books Fetched");
        books.forEach(System.out::println);

        // Find Book
        int id = 3;
        Optional<Book> book = bookService.findById(id);
        if(book.isPresent()){
            System.out.println(book.get());
        }else{
            System.out.println("Book Not Found For Id : "+id);
        }

    }
}