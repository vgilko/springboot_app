package ru.gilko.javalessons.SpringBootApp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gilko.javalessons.SpringBootApp.domain.Users;
import ru.gilko.javalessons.SpringBootApp.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Users user) {
        System.out.println(user);
        Users savedUser = userRepository.save(user);

        if (Objects.equals(savedUser, user)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity<List<Users>> list() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Optional<Users> user) {
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(user.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id,
                                    @RequestBody Users user) {
        Users userFromDataBase = userRepository.getById(id);

        BeanUtils.copyProperties(user, userFromDataBase, "id");

        userRepository.save(userFromDataBase);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            userRepository.deleteById(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch  (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

//    // name gender age
//    @GetMapping("/find")
//    public ResponseEntity<?> findUser (@PathVariable(name = "name", required = false) String name,
//                                       @PathVariable(name = "age", required = false) Optional<Integer> age,
//                                       @PathVariable(name = "gender", required = false) Gender gender) {
//        Predicate<Users> generalPredicate = null;
//        List<Predicate<Users>> predicates = new ArrayList<>();
//
//        if (name != null) {
//            predicates.add(user -> Objects.equals(user.getName(), name));
//        }
//
//        age.ifPresent(integer -> predicates.add(user -> user.getAge() == integer));
//
//        if (gender != null) {
//            predicates.add(user -> Objects.equals(user.getGender(), gender));
//        }
//
//        for (Predicate<Users> predicate : predicates) {
//            if (generalPredicate == null) {
//                generalPredicate = predicate;
//            } else {
//                generalPredicate = generalPredicate.and(predicate);
//            }
//        }
//
////        userRepository.findAll(generalPredicate);
//    }
}
