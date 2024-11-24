package com.schema.reader.controller;

import com.schema.reader.model.Book;
import com.schema.reader.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController // This class will handle HTTP requests and return data directly (in this case, JSON responses)
@RequestMapping("/api/books") // Base path for all endpoints in this controller
public class BookController {

    private final BookService bookService;

    @Autowired // Automatically injects BookService into this controller
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping // This endpoint handles HTTP GET requests to retrieve all books
    public Flux<Book> getAllBooks() {
        return bookService.getAllBooks(); // Returns all books as a Flux (multiple items)
    }

    @GetMapping("/{id}") // This endpoint retrieves a single book by its ID
    public Mono<Book> getBookById(@PathVariable String id) {
        return bookService.getBookById(id); // Returns the book with the given ID
    }

    @PostMapping // This endpoint handles HTTP POST requests to create a new book
    @ResponseStatus(HttpStatus.CREATED) // Sets the response status to 201 (Created) when a book is created
    public Mono<Book> createBook(@RequestBody Book book) {
        return bookService.createBook(book); // Passes the book data to the service to save it to the database
    }

    @PostMapping("/bulk")
    public Flux<Book> createBooks(@RequestBody List<Book> books) {
        return bookService.createBooks(books);
    }


    @DeleteMapping("/{id}") // This endpoint deletes a book by its ID
    @ResponseStatus(HttpStatus.NO_CONTENT) // Sets the response status to 204 (No Content) after deletion
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id); // Calls the service to delete the book by ID
    }

    @PutMapping("/{id}") // Full update of a book by its ID
    public Mono<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        return bookService.updateBook(id, book); // Pass the ID and book to the service layer
    }

    @PatchMapping("/{id}") // Partial update of a book by its ID
    public Mono<Book> partialUpdateBook(@PathVariable String id, @RequestBody Book book) {
        return bookService.partialUpdateBook(id, book); // Pass the ID and partial book to the service layer
    }
}

