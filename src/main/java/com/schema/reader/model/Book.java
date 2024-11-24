package com.schema.reader.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data // Lombok @Data automatically generates getters, setters, and other methods like toString() and equals()
@Document(collection = "books") // Marks this class as a MongoDB document, the collection name will be "books"
public class Book {
    private String id;
    private String title;
    private String author;
    private Integer year;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


}

