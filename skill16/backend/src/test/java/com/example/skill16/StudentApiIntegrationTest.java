package com.example.skill16;

import com.example.skill16.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void swaggerDocsShouldContainStudentsPath() throws Exception {
        MvcResult result = mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThat(body).contains("/students");
    }

    @Test
    void shouldCreateGetUpdateDeleteStudent() throws Exception {
        Student student = new Student("Nila", "nila@example.com", "Spring Boot");

        MvcResult createResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andReturn();

        Student created = objectMapper.readValue(createResult.getResponse().getContentAsString(), Student.class);
        Long id = created.getId();

        mockMvc.perform(get("/students/{id}", id))
                .andExpect(status().isOk());

        Student updatedPayload = new Student("Nila Updated", "nila.updated@example.com", "Microservices");
        mockMvc.perform(put("/students/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPayload)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/students/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/students/{id}", 999))
                .andExpect(status().isNotFound());

        mockMvc.perform(put("/students/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"A\",\"email\":\"a@example.com\",\"course\":\"Java\"}"))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/students/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnValidationErrorForInvalidRequest() throws Exception {
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"email\":\"wrong-email\",\"course\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}
