package io.simpolor.jpa.service;

import io.simpolor.jpa.domain.Student;
import io.simpolor.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getStudentTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getStudentList() {
        return studentRepository.findAll();

    }

    public Student getStudent(long seq) {
        return studentRepository.findById(seq).orElse(new Student());
    }

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student modifyStudent(Student student) {
        return studentRepository.save(student);
    }

    public long deleteStudent(long seq) {
        studentRepository.deleteById(seq);
        return seq;
    }

}
