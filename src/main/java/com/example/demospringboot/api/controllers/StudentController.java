package com.example.demospringboot.api.controllers;

import com.example.demospringboot.api.interfaces.StudentApi;
import com.example.demospringboot.model.internal.Student;
import com.example.demospringboot.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.example.demospringboot.api.RootPaths.BASE_API_V1_PATH;
import static com.example.demospringboot.api.interfaces.StudentApi.API_NAME;

@RestController
@RequestMapping(path = BASE_API_V1_PATH + API_NAME)
@RequiredArgsConstructor
public class StudentController implements StudentApi {

    private final StudentService studentService;

    @Override
    public Flux<Student> getStudents() {
        return studentService.getStudents();
    }

    @Override
    public Mono<Student> addNewStudent(final Student newStudent) {
        return studentService.addNewStudent(newStudent);
    }

    @Override
    public Mono<Void> deleteStudent(final UUID studentId) {
        return studentService.deleteStudent(studentId);
    }

    @Override
    public Mono<Student> updateStudent(final UUID studentId, Student updatedStudent) {
        return studentService.updateStudent(studentId, updatedStudent);
    }
}
