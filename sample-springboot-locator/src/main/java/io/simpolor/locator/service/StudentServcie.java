package io.simpolor.locator.service;

import io.simpolor.locator.domain.Student;

import java.util.List;

public interface StudentServcie {

    boolean supports(String s);

    int count();

    List<Student> getAll();

}
