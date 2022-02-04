package ru.gilko.javalessons.SpringBootApp.domain;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
@ToString(of = {"id", "name", "email"})
@EqualsAndHashCode(of = {"id"})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private boolean gender;

    private int age;
//    private long university;
    private String description;
}
