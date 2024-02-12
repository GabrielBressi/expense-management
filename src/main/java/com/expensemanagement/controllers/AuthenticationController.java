package com.expensemanagement.controllers;

import com.expensemanagement.dtos.AuthDTO;
import com.expensemanagement.dtos.LoginResponseDTO;
import com.expensemanagement.dtos.UserDTO;
import com.expensemanagement.infra.security.TokenService;
import com.expensemanagement.models.UserModel;
import com.expensemanagement.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody @Valid UserDTO data) throws Exception {
        if(userRepository.findByEmail(data.email()) != null) throw new Exception();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserModel newUser = new UserModel(
                data.firstName(),
                data.lastName(),
                data.email(),
                encryptedPassword,
                data.document(),
                data.balance(),
                data.role()
        );

        this.userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
