package io.simpolor.logging.service;

import io.simpolor.logging.domain.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    public long getTotalcount(){

        return 2L;
    }

    public List<Student> getAll(){

        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
        students.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화, 프로그래밍")));

        return students;
    }

    public Student get(long seq){

        return Student.builder()
                .seq(seq)
                .name("홍길동")
                .grade(2)
                .age(18)
                .hobby(Arrays.asList("드라마 감상"))
                .build();
    }

    public Student register(Student student){

        student.setSeq(1);

        return Student.builder()
                .seq(1)
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .hobby(student.getHobby())
                .build();
    }

    public Student modify(Student student){

        return student;
    }

    public long delete(long seq){

        return seq;
    }
}
