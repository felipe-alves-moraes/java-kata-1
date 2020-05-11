package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.Book;
import org.echocat.kata.java.part1.entity.Magazine;
import org.echocat.kata.java.part1.entity.Publication;
import org.echocat.kata.java.part1.service.CSVReader;
import org.echocat.kata.java.part1.service.PublicationService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class MainApp {

    public static void main(String[] args) throws FileNotFoundException {
        CSVReader csvReader = new CSVReader();
        PublicationService service = new PublicationService(csvReader);

        List<Publication> publications = service.getAllPublications();
        publications.forEach(System.out::println);

        System.out.println("Searching book with ISBN 1024-5245-8584");

        Optional<Publication> publicationByIsbn = service.findByIsbn("1024-5245-8584");

        if (publicationByIsbn.isPresent()) {
            System.out.println("Found: " + publicationByIsbn.get());
        } else {
            System.out.println("Not found!");
        }

        List<Publication> publicationByAuthorsEmail =
                service.findAllPublicationsByAuthor("null-ferdinand@echocat.org");

        System.out.println("Publications with the email: null-ferdinand@echocat.org");
        publicationByAuthorsEmail.forEach(System.out::println);

        System.out.println("Sorting Magazines and Books by title");
        service.sortPublicationsByTitle().forEach(System.out::println);
    }

}
