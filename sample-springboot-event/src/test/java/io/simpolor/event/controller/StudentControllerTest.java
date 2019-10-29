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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={EventApplication.class})
@WebMvcTest(controllers = {StudentController.class})
public class StudentControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    protected ArgumentCaptor<Object> publishEventCaptor;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStudentRegister() throws Exception {

        Student student = new Student();
        student.setName("홍길동");
        student.setGrade(2);
        student.setAge(17);
        student.setHobby(Arrays.asList("축구"));

        String json = objectMapper.writeValueAsString(student);

        this.mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());


        // mockMvc에서는 이벤트 테스트가 다른 방법으로 이루어지는 듯함
    }
}
