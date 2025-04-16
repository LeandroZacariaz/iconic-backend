package com.ecommerce.user_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.user_service.domain.User;
import com.ecommerce.user_service.domain.enums.RoleEnumUser;
import com.ecommerce.user_service.repository.UserRepository;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@ecommerce.com").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@ecommerce.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(RoleEnumUser.ADMIN);
                userRepository.save(admin);
                System.out.println("Usuario ADMIN creado: admin@ecommerce.com / admin123");
            } else {
                System.out.println("El usuario ADMIN ya existe.");
            }
        };
    }
}