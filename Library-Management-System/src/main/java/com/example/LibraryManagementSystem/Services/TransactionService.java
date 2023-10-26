package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Entities.Book;
import com.example.LibraryManagementSystem.Entities.LibraryCard;
import com.example.LibraryManagementSystem.Entities.Transaction;
import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private BookRepository bookRepository;

    private static final int MAX_NO_OF_BOOKS = 3;
    private static final int FINE_PER_DAY = 5;

    public String issueBook(int bookId, int cardId) throws Exception{
        Transaction transaction=new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        Book book=bookRepository.findById(bookId).get();

        LibraryCard card = libraryCardRepository.findById(cardId).get();

        if(!book.isAvailable()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            return "Book is currently unavailable.";
        }
        if(!card.getCardStatus().equals(CardStatus.ACTIVE)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            return "Card is not active.";
        }

        if(card.getNoOfBooksIssued() >= MAX_NO_OF_BOOKS){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            return "Maximum number of books already borrowed.";
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        book.setAvailable(false);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued() + 1);

        return "Book issued.";
    }

    public String returnBook(Integer bookId, Integer cardId){
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = libraryCardRepository.findById(cardId).get();

        Transaction transaction = transactionRepository.findTransaction(book,card,TransactionStatus.ISSUED);
        Date issueDate = transaction.getCreatedOn();

        long milliSeconds = Math.abs(System.currentTimeMillis() - issueDate.getTime());
        long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);

        int fineAmount = 0;
        if(days > 15){
            fineAmount = Math.toIntExact((days - 15) * FINE_PER_DAY);
        }

        Transaction newTransaction = new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        newTransaction.setBook(book);
        newTransaction.setLibraryCard(card);

        book.setAvailable(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued() - 1);

        book.getTransactionsList().add(newTransaction);
        card.getTransactionsList().add(newTransaction);

        transactionRepository.save(newTransaction);
        return "Book returned.";
    }
}
