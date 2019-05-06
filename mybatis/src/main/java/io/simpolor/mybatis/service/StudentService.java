package io.simpolor.mybatis.service;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public int getStudentTotalCount() {
        return studentRepository.selectStudentTotalCount();
    }

    public Student getStudent(int seq) {
        return studentRepository.selectStudent(seq);
    }

    public int registerStudent(Student student) {
        return studentRepository.insetStudent(student);
    }

    public int modifyStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    public int deleteStudent(int seq) {
        return studentRepository.deleteStudent(seq);
    }

}
