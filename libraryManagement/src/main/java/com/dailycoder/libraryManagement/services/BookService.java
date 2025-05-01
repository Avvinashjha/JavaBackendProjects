package com.dailycoder.libraryManagement.services;
import com.dailycoder.libraryManagement.entities.Book;

import java.util.List;


public interface BookService {

     List<Book> getAllBooks();
     Book getBookById(int id);
     Book addBook(Book book);
     void updateBook(int id, Book book);
     void deleteBook(int id);
}
