package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Entities.Author;
import com.example.LibraryManagementSystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return "";
    }

    @GetMapping("/findAllAuthorNames")
    public List<String> authorNames(){
        return authorService.getAllAuthorNames();
    }


    @GetMapping("/findAuthor/{id}")
    public ResponseEntity getAuthor(@PathVariable("id") Integer authorId){
        try{
            Author author = authorService.getAuthorById(authorId);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllBooks")
    public List<String> getAllBookNames(@RequestParam("authorId") Integer authorId){
        List<String> bookNames = authorService.booksList(authorId);
        return bookNames;
    }
}
