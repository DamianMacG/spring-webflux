package com.schema.reader.repository;

import com.schema.reader.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface is a Spring Data repository, responsible for database operations
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    // ReactiveMongoRepository provides CRUD operations for MongoDB in a reactive style
    // "Book" is the entity type, and "String" is the type of the ID field
}
