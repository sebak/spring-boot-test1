package com.example.demospringboot.model.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table
public class Student extends AbstractEntity{
    String name;
    @Transient // age ne sera pas persisté en db en tant que colonne car calculable à la volé
    int age;

    @NonNull
    String email;

    @NonNull
    LocalDate dateOfBirth;

    @Builder
    public Student(UUID id, String name, String email, LocalDate dateOfBirth) {
        super(id);
        this.name = name.trim();
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
