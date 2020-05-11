package org.echocat.kata.java.part1.service;

import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.Book;
import org.echocat.kata.java.part1.entity.Magazine;
import org.echocat.kata.java.part1.entity.Publication;

import java.io.BufferedReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PublicationService {

    private final CSVReader csvReader;
    private Map<String, Author> authors;
    private List<Publication> publications = new ArrayList<>();

    public PublicationService(CSVReader csvReader) {
        this.csvReader = csvReader;
        init();
    }

    private void init() {
        authors = csvReader.getAuthors("org/echocat/kata/java/part1/data/authors.csv");

        Function<BufferedReader, List<Book>> booksTransformer = csvReader.booksTransformer(authors);
        Function<BufferedReader, List<Magazine>> magazinesTransformer = csvReader.magazinesTransformer(authors);

        publications.addAll(csvReader.read("org/echocat/kata/java/part1/data/books.csv", authors,
                booksTransformer));
        publications.addAll(csvReader.read("org/echocat/kata/java/part1/data/magazines.csv",
                authors, magazinesTransformer));
    }

    public List<Publication> getAllPublications() {
        return Collections.unmodifiableList(publications);
    }

    public Optional<Publication> findByIsbn(String isbn) {
        return publications.stream().filter(publication -> isbn.equals(publication.getIsbn()))
                .findFirst();
    }

    public List<Publication> findAllPublicationsByAuthor(String email) {
        return publications.stream()
                .filter(publication -> publication.getAuthorsEmail().contains(email))
                .collect(Collectors.toList());
    }

    public List<Publication> sortPublicationsByTitle() {
        return publications.stream()
                .sorted(Comparator.comparing(Publication::getTitle))
                .collect(Collectors.toList());
    }
}
