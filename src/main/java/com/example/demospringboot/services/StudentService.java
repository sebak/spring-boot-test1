package com.example.demospringboot.services;

import com.example.demospringboot.model.internal.Student;
import com.example.demospringboot.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Flux<Student> getStudents() {
        return Flux.fromIterable(studentRepository.findAll());
    }

    public Mono<Student> addNewStudent(Student newStudent) {
        return Mono.just(newStudent)
                .map(this::validate)
                .map(studentRepository::save);
    }

    private Student validate(final Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

        if (studentOptional.isPresent()) {// TODO(create a custom exception to handle this well)
            throw new IllegalStateException("There is already a student with email " + student.getEmail());
        }

        return student;
    }

    /*public Mono<Void> deleteStudent(final UUID id) {
        return Mono.just(id)
                .map(studentRepository::findById)
                .switchIfEmpty(Mono.error(new Exception("No Student exist with id " + id)))
                .doOnNext(studentOptionalPresent -> studentRepository.deleteById(id))
                .then();
    }*/

    public Mono<Void> deleteStudent(final UUID id) {
        return Mono.just(id)
                .doOnNext(studentId-> studentRepository.deleteById(studentId))
                .then();
    }

    private void delete(final UUID studentId) {
        validateStudentExistence(studentId);
        studentRepository.deleteById(studentId);
    }

    public Mono<Student> updateStudent(final UUID studentId, final Student updatedStudent) {
        validateStudentExistence(studentId);
        Student currentStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("no student to update with id" + studentId));

        if (!currentStudent.getEmail().equals(updatedStudent.getEmail())) {
            validate(updatedStudent);
        }
        // never save updatedStudent, it will erase created date as null so set the current entity
        currentStudent.setEmail(updatedStudent.getEmail());
        currentStudent.setName(updatedStudent.getName());
        currentStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        return Mono.just(currentStudent)
                .map(studentRepository::save);
    }

    private void validateStudentExistence(final UUID studentId) {
        boolean studentExist = studentRepository.existsById(studentId);
        if (!studentExist) {
            throw new IllegalStateException("No Student exist with id " + studentId);
        }
    }
}
