package com.expensemanagement.controllers;

import com.expensemanagement.dtos.UserDTO;
import com.expensemanagement.models.UserModel;
import com.expensemanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = this.userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
