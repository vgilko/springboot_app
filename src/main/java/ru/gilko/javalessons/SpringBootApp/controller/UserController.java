package ru.gilko.javalessons.SpringBootApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Users user) {
        log.info("Request for creating user: " + user);
        Users savedUser = userRepository.save(user);

        if (Objects.equals(savedUser, user)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            log.error("User has not been created: " + user);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity<List<Users>> list() {
        log.info("Request for getting all users.");

        List<Users> allUsers = userRepository.findAll();
        log.info("Amount of extracted users: " + allUsers.size());

        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        log.info("Request for getting user with id = " + id);

        Optional<Users> user = userRepository.findById(id);

        if (user.isPresent()) {
            log.debug("Found user with id = " + id + ". " + user);
            return ResponseEntity.ok(user);
        } else {
            log.error("Unable to found user with id = " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody Users user) {
        log.info("Request for updating user with id = " + id);
        log.info("New user data: " + user);

        Users userFromDataBase = userRepository.getById(id);  // ????????????????????
        log.info("User before update: " + userFromDataBase);

        BeanUtils.copyProperties(user, userFromDataBase, "id");

        Users userAfterUpdate = userRepository.save(userFromDataBase);
//        log.info("User after update: " + userAfterUpdate);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
//        try {
            userRepository.deleteById(id);

            log.info("Deleted user with id = " + id);
            return ResponseEntity.status(HttpStatus.OK).build();
//        }
//        catch  (EmptyResultDataAccessException e) {
//            log.error("Unable to delete user with id = " + id);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
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
