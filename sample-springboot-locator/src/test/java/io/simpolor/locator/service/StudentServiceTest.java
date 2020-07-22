package io.simpolor.locator.service;

import io.simpolor.locator.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentServiceImpl.class})
public class StudentServiceTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    public void testGetStudentTotalCount(){

        // given, when
        long result = studentService.count();

        // then
        Assertions.assertThat(result)
                .isEqualTo(1);

    }

    @Test
    public void testGetStudentList(){

        // given, when
        List<Student> result = studentService.getAll();

        // then
        Assertions.assertThat(result)
                .isNotEmpty()
                .hasSize(1)
                .first()
                .extracting(Student::getName)
                .isEqualTo("김나희");
    }

}
