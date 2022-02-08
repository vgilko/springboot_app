package ru.gilko.javalessons.SpringBootApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gilko.javalessons.SpringBootApp.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

}
