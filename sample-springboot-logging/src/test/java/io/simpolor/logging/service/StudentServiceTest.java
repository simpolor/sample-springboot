package io.simpolor.logging.service;

import io.simpolor.logging.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentService.class})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testGetStudentTotalCount(){

        // given, when
        long result = studentService.getTotalcount();

        // then
        Assertions.assertThat(result)
                .isEqualTo(2L);

    }

    @Test
    public void testGetStudentList(){

        // given
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
        students.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));

        // when
        List<Student> result = studentService.getAll();

        // then
        Assertions.assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .first()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }

    @Test
    public void testGetStudent(){

        // given
        long seq = 1;

        // when
        Student result = studentService.get(seq);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student::getName)
                .isEqualTo("홍길동");
    }

    @Test
    public void testRegisterStudent(){

        // given
        long seq = 1;
        Student student = new Student(0, "단순색", 1, 17, Arrays.asList("축구"));

        // when
        Student result = studentService.register(student);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }

    @Test
    public void testModifyStudent(){

        // given
        long seq = 1;
        Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

        // when
        Student result = studentService.modify(student);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }

    @Test
    public void testDeleteStudent(){

        // given
        long seq = 1;

        // when
        long result = studentService.delete(seq);

        // then
        Assertions.assertThat(result)
                .isEqualTo(1L);
    }

}
