package com.example.skill16.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Skill 16 Student CRUD API",
                version = "1.0",
                description = "Swagger/OpenAPI documentation for Student Management CRUD operations",
                contact = @Contact(name = "FSAD Team", email = "fsad@example.com"),
                license = @License(name = "Educational Use")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server")
        }
)
public class OpenApiConfig {
}
