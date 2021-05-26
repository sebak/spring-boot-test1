package com.example.demospringboot.api.interfaces;

import com.example.demospringboot.model.internal.Student;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StudentApi {
    String API_NAME = "/student";

    @GetMapping
    Flux<Student> getStudents();

    @PostMapping
    Mono<Student> addNewStudent(@RequestBody @Validated Student newStudent);

    @DeleteMapping(path = "/{studentId}")
    Mono<Void> deleteStudent(@PathVariable("studentId") @NonNull UUID studentId);

    @PutMapping(path = "/{studentId}")
    Mono<Student> updateStudent(@PathVariable("studentId") @NonNull UUID studentId, @RequestBody @Validated Student updatedStudent);
}
