package org.echocat.kata.java.part1.entity;

public class Author {
    private String email;
    private String firstName;
    private String lastName;

    public Author(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "email='" + email + '\'' +
                '}';
    }
}
