package io.simpolor.locator.locator;

import io.simpolor.locator.service.StudentServcie;
import io.simpolor.locator.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SvcLocator {

    private final List<StudentServcie> studentServcies;

    @Autowired
    private StudentServiceImpl studentServcie;

    @Autowired
    public SvcLocator(List<StudentServcie> studentServcies) {
        this.studentServcies = studentServcies;
    }

    public StudentServcie getStudentService(String key) {
        return studentServcies.stream().filter(api-> api.supports(key))
                .findFirst().orElse(studentServcie);
    }
}
