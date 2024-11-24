package com.schema.reader.service;

import com.schema.reader.model.Book;
import com.schema.reader.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

@Service // Marks this class as a service to be managed by Spring's dependency injection system
public class BookService {

    private final BookRepository bookRepository;

    @Autowired // Automatically injects the BookRepository into this class
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> getAllBooks() {
        return bookRepository.findAll(); // Returns all books as a Flux (reactive stream of multiple items)
    }

    public Mono<Book> getBookById(String id) {
        return bookRepository.findById(id); // Returns a single book by its ID as a Mono (reactive stream for a single item)
    }

    public Mono<Book> createBook(Book book) {
        return bookRepository.save(book); // Saves a new book to the database
    }

    public Flux<Book> createBooks(List<Book> books) {
        return bookRepository.saveAll(books); // Saves all books to the database
    }


    public Mono<Void> deleteBook(String id) {
        return bookRepository.deleteById(id); // Deletes a book by its ID, Mono<Void> indicates no value is returned after deletion
    }

    public Mono<Book> updateBook(String id, Book book) {
        return bookRepository.findById(id) // Find the book by its ID
                .flatMap(existingBook -> {
                    // Update all fields with the new data
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setYear(book.getYear());

                    // Save and return the updated book
                    return bookRepository.save(existingBook);
                })
                .switchIfEmpty(Mono.empty()); // Return empty Mono if book is not found
    }

    public Mono<Book> partialUpdateBook(String id, Book book) {
        return bookRepository.findById(id) // Find the book by its ID
                .flatMap(existingBook -> {
                    // Only update fields that are non-null in the request
                    if (book.getTitle() != null) {
                        existingBook.setTitle(book.getTitle());
                    }
                    if (book.getAuthor() != null) {
                        existingBook.setAuthor(book.getAuthor());
                    }
                    if (book.getYear() != null) {
                        existingBook.setYear(book.getYear());
                    }

                    // Save and return the partially updated book
                    return bookRepository.save(existingBook);
                })
                .switchIfEmpty(Mono.empty()); // Return empty Mono if book is not found
    }


}
