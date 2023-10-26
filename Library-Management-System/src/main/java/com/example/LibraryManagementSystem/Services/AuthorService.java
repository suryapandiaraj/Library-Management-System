package com.example.LibraryManagementSystem.Services;


import com.example.LibraryManagementSystem.Entities.Author;
import com.example.LibraryManagementSystem.Entities.Book;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author){
        authorRepository.save(author);
        return "Author added.";
    }

    public List<String> getAllAuthorNames(){
        List<String> authorNames = new ArrayList<>();
        for(Author author : authorRepository.findAll()){
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }

    public Author getAuthorById(Integer authorId) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent()){
            throw new Exception("Id is invalid.");
        }

        return optionalAuthor.get();
    }

    public List<String> booksList(Integer authorId){
        List<String> bookNames = new ArrayList<>();
        Author author = authorRepository.findById(authorId).get();
        for(Book book : author.getBookList()){
            bookNames.add(book.getBookName());
        }
        return bookNames;
    }
}
