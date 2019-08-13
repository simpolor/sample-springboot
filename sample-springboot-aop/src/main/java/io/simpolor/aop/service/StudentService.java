package io.simpolor.aop.service;

import io.simpolor.aop.domain.Student;
import io.simpolor.aop.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public int getStudentTotalCount() {
        return studentRepository.selectStudentTotalCount();
    }

    public List<Student> getStudentList() {
        return studentRepository.selectStudentList();
    }

    public Student getStudent(long seq) {
        if(studentRepository.selectStudent(seq) != null){
            return studentRepository.selectStudent(seq);
        }
        return new Student();
    }

    public Student registerStudent(Student student) {
        log.info("Service student.toString() : {}", student.toString());
        long result = studentRepository.insertStudent(student);
        if(result > 0){
            student.setSeq(result);
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

    public long deleteStudent(long seq) {
        studentRepository.deleteStudent(seq);
        return seq;
    }

}
