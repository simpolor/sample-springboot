package io.simpolor.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.event.EventApplication;
import io.simpolor.event.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerMockTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    protected ArgumentCaptor<Object> publishEventCaptor;

    @InjectMocks
    private StudentController studentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStudentRegister() {

        Student student = new Student();
        student.setName("홍길동");
        student.setGrade(2);
        student.setAge(17);
        student.setHobby(Arrays.asList("축구"));

        Student result = studentController.studentRegister(student);

        verify(applicationEventPublisher).publishEvent(publishEventCaptor.capture());

        List<Object> capturedEvents = publishEventCaptor.getAllValues();
        System.out.println(capturedEvents.size());
    }

    @Test
    public void testStudentRegister2() {

        Student student = new Student();
        student.setName("홍길동");
        student.setGrade(2);
        student.setAge(17);
        student.setHobby(Arrays.asList("축구"));

        Student result = studentController.studentRegister(student);
        Student result2 = studentController.studentRegister(student);

        verify(applicationEventPublisher, Mockito.times(2)).publishEvent(publishEventCaptor.capture());

        List<Object> capturedEvents = publishEventCaptor.getAllValues();
        System.out.println(capturedEvents.size());
    }
}
