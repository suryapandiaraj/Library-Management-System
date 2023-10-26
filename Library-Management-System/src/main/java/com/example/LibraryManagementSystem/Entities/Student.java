package com.example.LibraryManagementSystem.Entities;

import jakarta.persistence.*;

@Entity
@Table(name= "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
        private Long studentId;         // primary key

    @Column(name="student_name")
        private String studentName;

    @Column(name="student_email")
        private String studentEmail;

    @Column(name="contact_no", unique=true, nullable = false)
        private String studentMobNo;

    @OneToOne(mappedBy = "student",cascade=CascadeType.ALL)
        private LibraryCard studentCard;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentMobNo() {
        return studentMobNo;
    }

    public void setStudentMobNo(String studentMobNo) {
        this.studentMobNo = studentMobNo;
    }

    public LibraryCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(LibraryCard studentCard) {
        this.studentCard = studentCard;
    }
}
