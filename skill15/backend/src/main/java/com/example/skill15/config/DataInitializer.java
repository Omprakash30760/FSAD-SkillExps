package com.example.skill15.config;

import com.example.skill15.model.Role;
import com.example.skill15.model.User;
import com.example.skill15.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.findByUsername("admin").orElseGet(() ->
                    userRepository.save(new User("admin", passwordEncoder.encode("admin123"), Role.ADMIN))
            );

            userRepository.findByUsername("employee").orElseGet(() ->
                    userRepository.save(new User("employee", passwordEncoder.encode("emp123"), Role.EMPLOYEE))
            );
        };
    }
}
