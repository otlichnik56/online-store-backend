package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findByUsername(String username);

}
