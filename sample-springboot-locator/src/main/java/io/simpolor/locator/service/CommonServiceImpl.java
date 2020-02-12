package io.simpolor.locator.service;

import io.simpolor.locator.domain.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommonServiceImpl implements StudentServcie {

    @Override
    public boolean supports(String s){
        return "COMMON".equalsIgnoreCase(s);
    }

    @Override
    public int count(){
        return 2;
    }

    @Override
    public List<Student> getAll(){

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "김영희", 2, 18, Arrays.asList()));
        students.add(new Student(2, "김철수", 1, 17, Arrays.asList("축구")));

        return students;
    }

}
