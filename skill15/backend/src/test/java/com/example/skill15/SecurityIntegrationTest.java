package com.example.skill15;

import com.example.skill15.dto.LoginRequest;
import com.fasterxml.jackson.databind.JsonNode;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginWithValidCredentialsShouldReturnJwtToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin123");

        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        assertThat(json.get("token").asText()).isNotBlank();
        assertThat(json.get("role").asText()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    void loginWithInvalidCredentialsShouldFail() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("wrong-password");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminEndpointWithoutTokenShouldBeForbidden() throws Exception {
        mockMvc.perform(post("/admin/add"))
                .andExpect(status().isForbidden());
    }

    @Test
    void employeeTokenShouldNotAccessAdminEndpoint() throws Exception {
        String employeeToken = loginAndGetToken("employee", "emp123");

        mockMvc.perform(delete("/admin/delete")
                        .header("Authorization", "Bearer " + employeeToken)
                        .param("employeeId", "E101"))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminTokenShouldAccessAdminEndpoint() throws Exception {
        String adminToken = loginAndGetToken("admin", "admin123");

        mockMvc.perform(post("/admin/add")
                        .header("Authorization", "Bearer " + adminToken)
                        .param("employeeName", "Alice"))
                .andExpect(status().isOk());
    }

    @Test
    void employeeTokenShouldAccessEmployeeProfile() throws Exception {
        String employeeToken = loginAndGetToken("employee", "emp123");

        mockMvc.perform(get("/employee/profile")
                        .header("Authorization", "Bearer " + employeeToken))
                .andExpect(status().isOk());
    }

    @Test
    void invalidTokenShouldFailForSecuredEndpoint() throws Exception {
        mockMvc.perform(get("/employee/profile")
                        .header("Authorization", "Bearer invalid.jwt.token"))
                .andExpect(status().isForbidden());
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        return json.get("token").asText();
    }
}
