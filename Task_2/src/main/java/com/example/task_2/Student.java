package com.example.task_2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private int age;
    private double mathScore;
    private double physicsScore;
    private double chemistryScore;
    private double gpa;
    private String academicPerformance;

    public Student(String name, String gender, int age, double mathScore, double physicsScore, double chemistryScore) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.mathScore = mathScore;
        this.physicsScore = physicsScore;
        this.chemistryScore = chemistryScore;
        calculateGPA();
        determineAcademicPerformance();
    }

    public void calculateGPA() {
        this.gpa = (mathScore + physicsScore + chemistryScore) / 3;
    }

    public void determineAcademicPerformance() {
        if (gpa >= 8) {
            this.academicPerformance = "Giỏi";
        } else if (gpa >= 6.5) {
            this.academicPerformance = "Khá";
        } else if (gpa >= 5) {
            this.academicPerformance = "Trung Bình";
        } else {
            this.academicPerformance = "Yếu";
        }
    }
}
