package com.dailycoder.libraryManagement.services.impl;

import com.dailycoder.libraryManagement.repositories.BookCopiesRepository;
import com.dailycoder.libraryManagement.services.BookCopiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCopiesServerImpl implements BookCopiesService {
    @Autowired
    private BookCopiesRepository bookCopiesRepository;


    @Override
    public int getAvailableCopies(int bookId) {
        return bookCopiesRepository.findAllByBookId(bookId);
    }

    @Override
    public void updateBookCopies(int bookId, boolean isAvailable) {
         bookCopiesRepository.update(bookId, isAvailable);
    }
}
