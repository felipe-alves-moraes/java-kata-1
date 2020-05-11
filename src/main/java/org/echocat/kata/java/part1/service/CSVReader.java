package org.echocat.kata.java.part1.service;

import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.Book;
import org.echocat.kata.java.part1.entity.Magazine;
import org.echocat.kata.java.part1.entity.Publication;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {
    private static final String DELIMITER = ";";
    private static final String COMMA = ",";

    public Map<String, Author> getAuthors(String file) {
        Map<String, Author> authors = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(Paths.get("", "src/main/resources").resolve(file).toString())))) {
            return br.lines().skip(1).map(line -> {
                String[] values = line.split(DELIMITER);
                return new Author(values[0], values[1], values[2]);
            }).collect(Collectors.toMap(Author::getEmail, author -> author));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authors;
    }


    public  <T extends Publication> List<T> read(String file, Map<String, Author> authors,
                                                          Function<BufferedReader, List<T>> maker) {
        List<T> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(Paths.get("", "src/main/resources").resolve(file).toString())))) {
            return maker.apply(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Function<BufferedReader, List<Book>> booksTransformer(Map<String, Author> authors) {
        return bufferedReader -> bufferedReader.lines().skip(1).map(line -> {
            String[] values = line.split(DELIMITER);
            List<Author> authorsFromBook = Stream.of(values[2].split(COMMA))
                    .map(authors::get)
                    .collect(Collectors.toList());
            return new Book(values[0], values[1], authorsFromBook, values[3]);
        }).collect(Collectors.toList());
    }

    public Function<BufferedReader, List<Magazine>> magazinesTransformer(Map<String, Author> authors) {
        return bufferedReader -> bufferedReader.lines().skip(1).map(line -> {
            String[] values = line.split(DELIMITER);
            List<Author> authorsFromBook = Stream.of(values[2].split(COMMA))
                    .map(authors::get)
                    .collect(Collectors.toList());
            LocalDate publishedAt = LocalDate.parse(values[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return new Magazine(values[0], values[1], authorsFromBook, publishedAt);
        }).collect(Collectors.toList());
    }
}
