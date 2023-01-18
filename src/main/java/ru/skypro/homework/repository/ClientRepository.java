package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
