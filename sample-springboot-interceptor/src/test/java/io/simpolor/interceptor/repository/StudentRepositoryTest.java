package io.simpolor.interceptor.repository;

import io.simpolor.interceptor.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentRepository.class})
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSelectStudentTotalCount(){

        // given, when
        long result = studentRepository.selectStudentTotalCount();

        // then
        Assertions.assertThat(result)
                .isEqualTo(3);

    }

    @Test
    public void testSelectStudentList(){

        // given, when
        List<Student> result = studentRepository.selectStudentList();

        // then
        Assertions.assertThat(result)
                .isNotEmpty()
                .first()
                .extracting(Student :: getName)
                .isEqualTo("단순색");

    }

    @Test
    public void testSelectStudent(){

        // given,
        int seq = 1;

        // when
        Student result = studentRepository.selectStudent(seq);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student :: getName)
                .isEqualTo("단순색");

    }

    @Test
    public void testInsertStudent(){

        // given,
        Student student = new Student(3L, "단순색", 1, 17, Arrays.asList("축구"));

        // when
        long result = studentRepository.insertStudent(student);

        // then
        Assertions.assertThat(result)
                .isEqualTo(3L);

    }

    @Test
    public void testUpdateStudent(){

        // given,
        Student student = new Student(1L, "단순색", 1, 17, Arrays.asList("축구"));

        // when
        long result = studentRepository.updateStudent(student);

        // then
        Assertions.assertThat(result)
                .isEqualTo(1L);

    }

    @Test
    public void testDeleteStudent(){

        // given,
        long seq = 1;

        // when
        long result = studentRepository.deleteStudent(seq);

        // then
        Assertions.assertThat(result)
                .isEqualTo(1L);

    }
}
