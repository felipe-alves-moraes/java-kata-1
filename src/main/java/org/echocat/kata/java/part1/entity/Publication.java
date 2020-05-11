package org.echocat.kata.java.part1.entity;

import java.util.List;

public interface Publication {

    String getIsbn();
    List<String> getAuthorsEmail();
    String getTitle();
}
