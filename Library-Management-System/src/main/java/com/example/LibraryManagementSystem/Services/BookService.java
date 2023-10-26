package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Entities.Book;
import com.example.LibraryManagementSystem.Entities.Author;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book, Integer authorId) throws Exception{
        Optional<Author> optionalAuthor =authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
            throw new Exception("Invalid Author ID.");
        }
        return "";
    }
}
