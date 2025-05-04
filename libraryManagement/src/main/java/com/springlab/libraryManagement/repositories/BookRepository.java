package com.springlab.libraryManagement.repositories;

import com.springlab.libraryManagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "UPDATE Book b SET b.availableCopies = b.availableCopies - 1 WHERE b.bookId = ?1", nativeQuery = true)
    void update(int id, Book book);
}
