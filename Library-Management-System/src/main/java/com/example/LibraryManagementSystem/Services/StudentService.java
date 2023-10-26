package com.example.LibraryManagementSystem.Services;


import com.example.LibraryManagementSystem.Entities.Student;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    private JavaMailSender mailSender;
    public String addStudent(Student student){
        studentRepository.save(student);
        return "Student added.";
    }
}
