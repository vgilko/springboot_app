package ru.gilko.javalessons.SpringBootApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.gilko.javalessons.SpringBootApp.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table
@Data
@ToString(of = {"id", "name", "email"})
@EqualsAndHashCode(of = {"id"})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String name;

    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Email is not valid")
    @NotBlank
    private String email;

    @NotNull(message = "Gender is not valid.")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @PositiveOrZero(message = "Age is not valid")
    private int age;
//    private Long university;
    @NotBlank
    private String description;
}
