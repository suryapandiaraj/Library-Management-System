package com.example.LibraryManagementSystem.Entities;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="library_card")
public class LibraryCard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardNumber;

    @OneToOne
    @JoinColumn
        private Student student;

    @Enumerated(value = EnumType.STRING)
        private CardStatus cardStatus;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
        private List<Transaction> transactionsList = new ArrayList<>();

    private int noOfBooksIssued;

    public int getNoOfBooksIssued() {
        return noOfBooksIssued;
    }

    public void setNoOfBooksIssued(int noOfBooksIssued) {
        this.noOfBooksIssued = noOfBooksIssued;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }
}
