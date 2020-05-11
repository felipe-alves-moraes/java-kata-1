package org.echocat.kata.java.part1.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Book implements Publication {
    private String title;
    private String isbn;
    private List<Author> authors;
    private String description;

    public Book(String title, String isbn, List<Author> authors, String description) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getAuthorsEmail() {
        return authors.stream().map(Author::getEmail).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", description='" + description + '\'' +
                '}';
    }
}
