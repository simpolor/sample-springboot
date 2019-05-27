package io.simpolor.elasticsearch.service;

import io.simpolor.elasticsearch.domain.Student;
import io.simpolor.elasticsearch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getStudentTotalCount() {
        return studentRepository.selectStudentTotalCount();
    }

    public List<Student> getStudentList() {
        return studentRepository.selectStudentList();
    }

    public Student getStudent(String id) {
        if(studentRepository.selectStudent(id) != null){
            return studentRepository.selectStudent(id);
        }
        return new Student();
    }

    public Student registerStudent(Student student) {
        if(studentRepository.insertStudent(student) > 0){
            return student;
        }
        return new Student();
    }

    public Student modifyStudent(Student student) {
        if(studentRepository.updateStudent(student) > 0){
            return student;
        }
        return new Student();
    }

    public String deleteStudent(String id) {
        studentRepository.deleteStudent(id);
        return id;
    }

}
