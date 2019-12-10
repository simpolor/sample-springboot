package io.simpolor.event.controller;

import io.simpolor.event.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

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
