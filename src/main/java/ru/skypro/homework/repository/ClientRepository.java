package ru.skypro.homework.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ru.skypro.homework.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM client WHERE username = ?1", nativeQuery = true)
    Client getUserName(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE client SET password = ?2 WHERE password = ?1", nativeQuery = true)
    void setNewPass(String currentPass, String newPass);


}
