package com.springlab.libraryManagement.repositories;

import com.springlab.libraryManagement.entities.BookCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopiesRepository extends JpaRepository<BookCopies, Integer> {
    // Custom query to find all copies of a book by its ID
    // This method will be implemented by Spring Data JPA
    @Query(value = "SELECT b FROM BookCopies b WHERE b.book.bookId = ?1")
    int findAllByBookId(int bookId);

    @Query(value = "UPDATE BookCopies b SET b.available = ?2 WHERE b.book.bookId = ?1")
    void update(int bookId, boolean status);
}
