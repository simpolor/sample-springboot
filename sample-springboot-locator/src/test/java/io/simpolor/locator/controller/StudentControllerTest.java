package io.simpolor.locator.controller;

import io.simpolor.locator.locator.SvcLocator;
import io.simpolor.locator.service.CommonServiceImpl;
import io.simpolor.locator.service.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {StudentServiceImpl.class, CommonServiceImpl.class, SvcLocator.class, StudentController.class})
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStudentTotalCountByParamAPI()throws Exception {

        // given when, then
        mockMvc.perform(get("/student/totalcount?p=API"))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$")
                                .value(is(1))
                )
                // .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testStudentTotalCountByParamCOMMON()throws Exception {

        // given when, then
        mockMvc.perform(get("/student/totalcount?p=COMMON"))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$")
                                .value(is(2))
                )
                // .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testStudentListByParamAPI()throws Exception {


        // given, when, then
        mockMvc.perform(get("/student/list")
            .param("p", "API")
        )
            .andExpect(status().isOk())
            .andExpect(
                    MockMvcResultMatchers
                            .jsonPath("$.[0].name")
                            .value(is("김나희"))
            )
            // .andDo(MockMvcResultHandlers.print())
            .andReturn();
    }

    @Test
    public void testStudentListByParamCOMMON()throws Exception {


        // given, when, then
        mockMvc.perform(get("/student/list")
                .param("p", "COMMON")
        )
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.[0].name")
                                .value(is("김영희"))
                )
                // .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
