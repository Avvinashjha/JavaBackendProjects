package com.springlab.libraryManagement.controllers;

import com.springlab.libraryManagement.entities.Book;
import com.springlab.libraryManagement.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Library Management System Book",
        description = "Book operations for the Library Management System"
)
@RestController
@RequestMapping("/api/v1/lms/books")
public class BookController {

    // Autowire the BookService and BookCopiesService here
     @Autowired
     private BookService bookService;

     @GetMapping("/")
     public List<Book> getAllBooks() {
         return bookService.getAllBooks();
     }

     @Operation(
                summary = "Get a book by ID",
                description = "Retrieve a book from the library by its ID"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "201", description = "Book added successfully",
                     content = {@Content(mediaType = "application/json",
                             schema = @Schema(implementation = Book.class))}),
             @ApiResponse(responseCode = "400", description = "Invalid input provided",
                     content = @Content)
     })
     @PostMapping("/add")
     public ResponseEntity<Book> addBook(@RequestBody Book book) {
         Book savedBook = bookService.addBook(book);
         return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
     }
}
