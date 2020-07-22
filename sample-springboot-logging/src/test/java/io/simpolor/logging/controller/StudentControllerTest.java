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
@WebMvcTest(controllers = {StudentController.class})
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStudentTotalCount()throws Exception {

        // given
        when(studentService.getTotalcount()).thenReturn(2L);

        // when
        mockMvc.perform(get("/student/totalcount"))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$")
                                .value(is(2))
                )
                // .andDo(MockMvcResultHandlers.print())
                .andReturn();

        // then
        verify(studentService, times(1)).getTotalcount(); // success
    }

    @Test
    public void testStudentList()throws Exception {

        // given
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
        students.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));

        when(studentService.getAll()).thenReturn(students);

        // when
        mockMvc.perform(get("/student/list"))
            .andExpect(status().isOk())
            .andExpect(
                    MockMvcResultMatchers
                            .jsonPath("$.[0].name")
                            .value(is("단순색"))
            )
            // .andDo(MockMvcResultHandlers.print())
            .andReturn();

        // then
        verify(studentService, times(1)).getAll(); // success
    }

    @Test
    public void testStudentView()throws Exception {

        // given
        long seq = 1L;
        Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

        when(studentService.get(seq)).thenReturn(student);

        // when
        mockMvc.perform(get("/student/"+seq))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.name")
                                .value(is("단순색"))
                )
                // .andDo(MockMvcResultHandlers.print())
                .andReturn();

        // then
        verify(studentService, times(1)).get(anyLong()); // success
    }

    @Test
    public void testStudentRegister()throws Exception {

        // given
        long seq = 1L;
        Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

        when(studentService.register(student)).thenReturn(student);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        // when
        mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.name")
                                .value(is("단순색"))
                )
                .andReturn();

        // then
        verify(studentService, times(1)).register(any()); // success
    }

    @Test
    public void testStudentModify()throws Exception {

        // given
        long seq = 1L;
        Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

        when(studentService.modify(student)).thenReturn(student);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        // when
        mockMvc.perform(put("/student/"+seq)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.name")
                                .value(is("단순색"))
                )
                .andReturn();

        // then
        verify(studentService, times(1)).modify(any()); // success
    }

    @Test
    public void testStudentDelete()throws Exception {

        // given
        long seq = 1L;

        when(studentService.delete(seq)).thenReturn(seq);


        // when
        mockMvc.perform(delete("/student/"+seq))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$")
                                .value(is(1))
                )
                .andReturn();

        // then
        verify(studentService, times(1)).delete(anyLong()); // success
    }
}
