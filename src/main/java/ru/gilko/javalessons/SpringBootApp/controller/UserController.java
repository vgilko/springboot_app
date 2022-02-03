package ru.gilko.javalessons.SpringBootApp.controller;

import org.springframework.web.bind.annotation.*;
import ru.gilko.javalessons.SpringBootApp.domain.Users;
import ru.gilko.javalessons.SpringBootApp.exceptions.NotFoundException;
import ru.gilko.javalessons.SpringBootApp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> list() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public Users getOne(@PathVariable("id") Optional<Users> user) {
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        return user.get();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Optional<Users> user) {
        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        userRepository.deleteById(user.get().getId());
    }
}
