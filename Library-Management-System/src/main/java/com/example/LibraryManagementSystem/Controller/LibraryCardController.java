package com.example.LibraryManagementSystem.Controller;


import com.example.LibraryManagementSystem.Entities.LibraryCard;
import com.example.LibraryManagementSystem.Entities.Student;
import com.example.LibraryManagementSystem.Services.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("library_card")
public class LibraryCardController {

    @Autowired
    private LibraryCardService libraryCardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard(){
        LibraryCard card = libraryCardService.generateCard();
        String result = "Plain Card generated.";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/assignWithStudent")
    public ResponseEntity assignWithStudent(@RequestParam("cardId") Integer cardId, @RequestParam("studentId") Integer studentId){
        String result = libraryCardService.associateStudentWithCard(cardId, studentId);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
