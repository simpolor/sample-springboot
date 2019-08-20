package io.simpolor.aop.repository;

import io.simpolor.aop.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class StudentRepository {

    public int selectStudentTotalCount(){
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
        log.info("Repository student.toString() : {}", student.toString());

        // return 3/0;
        return 3;
    }

    public int updateStudent(Student student){
        return 1;
    }

    public int deleteStudent(long seq){
        return 1;
    }
}
