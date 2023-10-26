package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Entities.LibraryCard;
import com.example.LibraryManagementSystem.Entities.Student;
import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryCardService {

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public LibraryCard generateCard(){
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card=libraryCardRepository.save(card);
        return card;
    }

    public String associateStudentWithCard(Integer cardId, Integer studentId){

        Student student=studentRepository.findById(studentId).get();

        LibraryCard card=libraryCardRepository.findById(cardId).get();

        card.setCardStatus(CardStatus.ACTIVE);
        card.setStudent(student);

        return "Card assigned to Student";
    }
}
