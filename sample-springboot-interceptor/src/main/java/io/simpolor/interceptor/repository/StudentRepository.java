package io.simpolor.interceptor.repository;

import io.simpolor.interceptor.domain.Student;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class StudentRepository {

    public long selectStudentTotalCount(){
        return 3;
    }

    public List<Student> selectStudentList(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
        students.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));
        return students;
    }

    public Student selectStudent(long seq){
        return new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));
    }

    public long insertStudent(Student student){
        return 3;
    }

    public long updateStudent(Student student){
        return 1;
    }

    public long deleteStudent(long seq){
        return 1;
    }
}
