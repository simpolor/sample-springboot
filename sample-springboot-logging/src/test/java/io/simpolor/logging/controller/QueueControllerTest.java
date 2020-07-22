package io.simpolor.logging.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.logging.domain.Student;
import io.simpolor.logging.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {QueueController.class})
public class QueueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStudentQueueModify()throws Exception {

        // given
        long seq = 1L;
        Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        // when, then
        mockMvc.perform(put("/student/queue/"+seq)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.name")
                                .value(is("단순색"))
                )
                .andReturn();

    }
}
