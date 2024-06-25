package com.example.task_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setGender(studentDetails.getGender());
            student.setAge(studentDetails.getAge());
            student.setMathScore(studentDetails.getMathScore());
            student.setPhysicsScore(studentDetails.getPhysicsScore());
            student.setChemistryScore(studentDetails.getChemistryScore());
            student.calculateGPA();
            student.determineAcademicPerformance();
            return studentRepository.save(student);
        }).orElseGet(() -> {
            studentDetails.setId(id);
            return studentRepository.save(studentDetails);
        });
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Student> sortStudentsByGPA() {
        return studentRepository.findAll().stream()
                .sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()))
                .toList();
    }

    public List<Student> sortStudentsByName() {
        return studentRepository.findAll().stream()
                .sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
                .toList();
    }
}
