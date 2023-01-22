package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.skypro.homework.model.user.LoginReq;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.service.auth.AuthService;

import static ru.skypro.homework.model.user.Role.USER;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
        summary = "авторизация пользователя",
        description = "принимает логин и пароль, проверяет в базе среди зарегистрированных и если есть совпадение открывает доступ пользователю"
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(
        @Parameter(description = "принимает объект со значениями полей username и password", 
        schema = @Schema(implementation = LoginReq.class)
        )
        @RequestBody LoginReq req) {
        if (authService.login(req.getUsername(), req.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(
        summary = "регистрация пользователя",
        description = "принимает объект с регистрационными данными из формы пользовательского интерфейса и сохраняет пользвателя в базе данных"
        )
    @ApiResponse(responseCode = "201", description = "Пользователь создан")    
    @PostMapping("/register")
    public ResponseEntity<?> register(
        @Parameter(description = "принимает объект с регистрационными данными",
        schema = @Schema(implementation = RegisterReq.class)
        )
        @RequestBody RegisterReq req
        ) {
        Role role = req.getRole() == null ? USER : req.getRole();
        if (authService.register(req, role)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
