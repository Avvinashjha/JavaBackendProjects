package com.dailycoder.libraryManagement.services.impl;

import com.dailycoder.libraryManagement.entities.Book;
import com.dailycoder.libraryManagement.repositories.BookRepository;
import com.dailycoder.libraryManagement.services.BookCopiesService;
import com.dailycoder.libraryManagement.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    private BookCopiesService bookCopiesService;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) {
            throw new IllegalArgumentException("Book details cannot be null");
        }
        // Save the book and return the saved instance
        return bookRepository.save(book);
    }

    @Override
    public void updateBook(int id, Book book) {
        bookRepository.update(id, book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
