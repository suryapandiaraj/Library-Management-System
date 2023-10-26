package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Entities.Book;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId) throws Exception {
        try {
            String result = bookService.addBook(book, authorId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBooksByGenre")
    public List<String> getBooksByGenre(@RequestParam("genre") Genre genre){
        List<String> booksList = bookService.getBooksByGenre(genre);
        return booksList;
    }
}
