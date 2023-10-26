package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
