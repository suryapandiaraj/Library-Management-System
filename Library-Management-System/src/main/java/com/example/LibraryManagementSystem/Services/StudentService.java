package com.example.LibraryManagementSystem.Services;


import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    private JavaMailSender mailSender;
}
