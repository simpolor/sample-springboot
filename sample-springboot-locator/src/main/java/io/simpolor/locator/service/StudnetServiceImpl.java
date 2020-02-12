package io.simpolor.locator.service;

import io.simpolor.locator.domain.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudnetServiceImpl implements StudentServcie {

    @Override
    public boolean supports(String s){
        return "API".equalsIgnoreCase(s);
    }

    @Override
    public int count(){
        return 1;
    }

    @Override
    public List<Student> getAll(){

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "김나희", 2, 18, Arrays.asList("그림그리기")));

        return students;
    }
}
