package io.simpolor.aop.aop;

import io.simpolor.aop.config.AspectJAutoProxyConfig;
import io.simpolor.aop.domain.Student;
import io.simpolor.aop.repository.StudentRepository;
import io.simpolor.aop.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AspectJAutoProxyConfig.class, StudentAspect.class, StudentService.class})
public class StudentAspectTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    public void testRegisterStudent(){

        // given
        long seq = 1;
        Student student = new Student(0, "단순색", 1, 17, Arrays.asList("축구"));

        when(studentRepository.insertStudent(student)).thenReturn(seq);

        // when
        Student result = studentService.registerStudent(student);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }
}
