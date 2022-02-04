package ru.gilko.javalessons.SpringBootApp.controller;

import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gilko.javalessons.SpringBootApp.domain.Users;
import ru.gilko.javalessons.SpringBootApp.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public HttpStatus create(@RequestBody Users user) { // как информировать о невозможности сохранить?
        // и есть ли вообще ответы в виде HttpStatus?
        Users savedUser = userRepository.save(user);

        if (Objects.equals(savedUser, user)) {
            return HttpStatus.OK;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping
    public ResponseEntity<List<Users>> list() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getOne(@PathVariable("id") Optional<Users> user) {
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(user.get());
    }

    @PutMapping("{id}") // использовать /users/:id/edit или просто /users/:id в качестве ендпоинта
    public ResponseEntity<Users> update(@PathVariable("id") long id,
                                        @RequestBody Users user) {
        Users userFromDataBase = userRepository.getById(id);

        BeanUtils.copyProperties(user, userFromDataBase, "id");

        userRepository.save(userFromDataBase);

        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {
        try {
            userRepository.deleteById(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch  (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
