package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Entities.Book;
import com.example.LibraryManagementSystem.Entities.Author;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book, Integer authorId) throws Exception{
        Optional<Author> optionalAuthor =authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            throw new Exception("Invalid Author ID.");
        }
        Author author = optionalAuthor.get();
        book.setAuthor(author);
        author.getBookList().add(book);

        authorRepository.save(author);      //cascading effect will save book in bookRepo

        return "Book has been added to the DB.";
    }

    public List<String> getBooksByGenre(Genre genre){
        List<String> booksList = new ArrayList<>();
        for(Book book : bookRepository.findAll()){
            if(book.getGenre().equals(genre)){
                booksList.add(book.getBookName());
            }
        }
        return booksList;
    }
}
