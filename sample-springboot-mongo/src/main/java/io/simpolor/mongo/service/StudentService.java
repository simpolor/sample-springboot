package io.simpolor.mongo.service;

import io.simpolor.mongo.domain.Student;
import io.simpolor.mongo.repository.SequenceRepository;
import io.simpolor.mongo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final String STUDENT_SEQ_KEY = "hosting";

    @Autowired
    private SequenceRepository sequenceRepository;

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
        student.setSeq(sequenceRepository.getNextSequenceId(STUDENT_SEQ_KEY));
        return studentRepository.save(student);
    }

    public Student modifyStudent(Student student) {
        if(studentRepository.findById(student.getSeq()).isPresent()){
            return studentRepository.save(student);
        }
        return new Student();
    }

    public long deleteStudent(long seq) {
        studentRepository.deleteById(seq);
        return seq;
    }

}
