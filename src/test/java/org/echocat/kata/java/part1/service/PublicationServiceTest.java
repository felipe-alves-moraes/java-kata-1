package org.echocat.kata.java.part1.service;

import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.Book;
import org.echocat.kata.java.part1.entity.Magazine;
import org.echocat.kata.java.part1.entity.Publication;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

import static org.junit.Assert.*;

public class PublicationServiceTest {

    private PublicationService publicationService;

    @Before
    public void init() {
        Map<String, Author> authorMap = new HashMap<>();
        authorMap.put("test@test", new Author("test@test", "test", "null"));
        CSVReader reader = Mockito.mock(CSVReader.class);
        Mockito.when(reader.getAuthors(Mockito.anyString())).thenReturn(authorMap);
        Mockito.when(reader.read(Mockito.anyString(), Mockito.anyMap(), Mockito.any(Function.class))).thenReturn(
                Arrays.asList(
                        new Book("abc", "111", Collections.singletonList(authorMap.get(0)), "description"),
                new Magazine("aaa", "222", Collections.singletonList(authorMap.get(0)), LocalDate.now()))
        );
        publicationService = new PublicationService(reader);
    }

}