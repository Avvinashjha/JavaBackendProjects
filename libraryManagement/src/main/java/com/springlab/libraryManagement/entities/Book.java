package com.springlab.libraryManagement.entities;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

import java.util.List;

@Tag(
        name = "Library Management System Book Entity",
        description = "Book entity for the Library Management System"
)

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private String genre;
    private Integer publicationYear;
    private Integer totalCopies;
    private Integer availableCopies;

    // one-to-many relationship: A Book can have multiple BookCopies
    //The cascade = CascadeType.ALL ensures that operations like persist, merge, or remove on the Book entity are cascaded to its associated BookCopies
    //The orphanRemoval = true option ensures that if a BookCopy is removed from the list of copies, it will be deleted from the database.
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCopies> bookCopiesList;

    public Book(String title, String author, String publisher, String ISBN, String genre, Integer publicationYear, Integer totalCopies, Integer availableCopies, List<BookCopies> bookCopiesList) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.bookCopiesList = bookCopiesList;
    }

    public Book() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

}
