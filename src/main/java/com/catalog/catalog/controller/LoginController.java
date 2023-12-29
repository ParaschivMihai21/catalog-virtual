package com.catalog.catalog.controller;


import com.catalog.catalog.service.TokenService;
import com.catalog.catalog.dto.CredentialsDTO;
import com.catalog.catalog.dto.SignUpDTO;
import com.catalog.catalog.dto.UserDTO;
import com.catalog.catalog.exceptions.AppException;
import com.catalog.catalog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class LoginController {

    private final UserService userService;
    private final TokenService tokenService;

    public LoginController(UserService userService, TokenService tokenService) {
        this.tokenService = tokenService;
        this.userService = userService;

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid CredentialsDTO credentialsDTO)throws AppException {
        UserDTO userDto = userService.login(credentialsDTO);
        String token = tokenService.createToken(userDto);

        return ResponseEntity.ok()
                .body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpDTO signUpDTO) throws AppException {
        UserDTO createdUser = userService.register(signUpDTO);
        createdUser.setToken(tokenService.createToken(createdUser));
        return ResponseEntity.ok(createdUser);
    }


}
