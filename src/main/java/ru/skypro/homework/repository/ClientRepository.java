package ru.skypro.homework.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Client getUserName(@Param("username") String username);

    Client findByUsername(String username);

}
