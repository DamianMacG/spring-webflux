package com.schema.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication // Marks this as the main class to run the Spring Boot application
@EnableMongoRepositories("com.schema.reader.repository")
public class ReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReaderApplication.class, args); // Starts the Spring Boot application
	}
}
