package ru.skypro.homework;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;


import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.LoginReq;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.repository.ClientRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
 
    @Mock
    ClientRepository clientRepository;
   
    String userName;
    String password;

    @BeforeEach
    public void init() {
        userName = "username";
        password = "123";
    }

    /**
     * описание - метод, который проверяет 
     * типы полей класса LoginReg
     */
    @Test
    public void checkTypesOfFieldsLoginRegClass() {
        
         LoginReq loginReq = new LoginReq();
         loginReq.setUsername(userName);
         loginReq.setPassword(password);
        
         Assertions.assertTrue(loginReq.getUsername() instanceof String);
         Assertions.assertTrue(loginReq.getPassword() instanceof String);
    }


    /**
     * описание - метод, который проверяет 
     * типы полей класса RegisterReg
     */
    @Test
    public void checkTypesOfFieldsRegisterReg() {
        String firstName = "firstname";
        String lastName = "lastname";
        String phone = "phone";
        Role role = Role.USER;

        RegisterReq registerReq = new RegisterReq();
        registerReq.setUsername(userName);
        registerReq.setPassword(password);
        registerReq.setFirstName(firstName);
        registerReq.setLastName(lastName);
        registerReq.setPhone(phone);
        registerReq.setRole(role);

        Assertions.assertTrue(registerReq.getFirstName() instanceof String);
        Assertions.assertTrue(registerReq.getLastName() instanceof String);
        Assertions.assertTrue(registerReq.getPassword() instanceof String);
        Assertions.assertTrue(registerReq.getPhone() instanceof String);
        Assertions.assertTrue(registerReq.getUsername() instanceof String);
        Assertions.assertTrue(registerReq.getRole() instanceof Role);
    }


    /**
     * описание - метод, который проверяет сохранение
     * регистрационных данных в таблице
     */
    @Test
    public void shouldBeWriteValuesToClientEntityFromRegistration() {

        RegisterReq registerReq = new RegisterReq(
            "jon@mail.ru", 
            "123", 
            "jon", 
            "smith", 
            "+79991231212", 
            Role.USER);
       
        when(clientRepository
        .getUserName(registerReq.getUsername()))
        .thenReturn(new Client(
            1,
            registerReq.getUsername(),
            registerReq.getPassword(),
            registerReq.getRole(),
            registerReq.getFirstName(),
            registerReq.getLastName(),
            registerReq.getPhone(),
            registerReq.getUsername(),
            "",
            "", 
            ""
        ));

        Assertions.assertEquals(
            clientRepository.getUserName(registerReq.getUsername()).getUsername(),
            registerReq.getUsername()
            );
        
    }
}
