package io.simpolor.property.controller;

import io.simpolor.property.component.ApplicationProperty;
import io.simpolor.property.component.DatabaseProperty;
import io.simpolor.property.config.PropertyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ApplicationProperty.class, DatabaseProperty.class, PropertyConfig.class, WelcomeController.class})
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStudentTotalCount()throws Exception {

        // given, when, then
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$")
                                .value(is("Springboot Sample Property"))
                )
                // .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testProperty()throws Exception {

        // given, when, then
        mockMvc.perform(get("/property"))
            .andExpect(status().isOk())
             .andDo(MockMvcResultHandlers.print())
            .andReturn();
    }
}
