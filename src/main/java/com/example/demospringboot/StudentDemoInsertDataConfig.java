package com.example.demospringboot;

import com.example.demospringboot.model.internal.Student;
import com.example.demospringboot.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Configuration
public class StudentDemoInsertDataConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            Student student1 = Student.builder()
                    .id(UUID.fromString("7b736204-b7bf-11eb-9a8c-34e6d72a5caa"))
                    .name("Paul Dupond").dateOfBirth(LocalDate.of(2001, 5, 17))
                    .email("paul@yahoo.fr").build();

            Student student2 = Student.builder()
                    .id(UUID.fromString("351b5778-b7c2-11eb-ae82-34e6d72a5caa"))
                    .name("Pierre Allane").dateOfBirth(LocalDate.of(2000, 5, 17))
                    .email("pierre@gmail.com").build();

            studentRepository.saveAll(List.of(student1, student2));
        };
    }
}
