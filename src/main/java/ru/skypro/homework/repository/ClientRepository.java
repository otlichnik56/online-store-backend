package ru.skypro.homework.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Client getUserName(@Param("username") String username);

    Client findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET password = ?2 WHERE password = ?1", nativeQuery = true)
    void setNewPass(String currentPass, String newPass);


}
