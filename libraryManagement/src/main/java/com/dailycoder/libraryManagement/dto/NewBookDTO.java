package com.dailycoder.libraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBookDTO {
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private String genre;
    private Integer publicationYear;
    private Integer totalCopies;
    private Integer availableCopies;
}
