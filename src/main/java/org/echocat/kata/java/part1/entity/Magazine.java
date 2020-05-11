package org.echocat.kata.java.part1.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Magazine implements Publication {
    private String title;
    private String isbn;
    private List<Author> authors;
    private LocalDate publishedAt;

    public Magazine(String title, String isbn, List<Author> authors, LocalDate publishedAt) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.publishedAt = publishedAt;
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

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    @Override
    public List<String> getAuthorsEmail() {
        return authors.stream().map(Author::getEmail).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
