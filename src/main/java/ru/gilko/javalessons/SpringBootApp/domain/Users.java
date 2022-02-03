package ru.gilko.javalessons.SpringBootApp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@ToString(of = {"id", "name", "email"})
@EqualsAndHashCode(of = {"id"})
public class Users {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
    private boolean gender;

    private int age;
//    private long university;
    private String description;
}
