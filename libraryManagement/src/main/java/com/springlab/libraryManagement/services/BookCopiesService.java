package com.springlab.libraryManagement.services;

import org.springframework.stereotype.Service;

public interface BookCopiesService {

    int getAvailableCopies(int bookId);

    void updateBookCopies(int bookId, boolean isAvailable);
}
