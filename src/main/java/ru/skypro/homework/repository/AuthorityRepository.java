package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findByUsername(String username);

}
