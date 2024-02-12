package com.expensemanagement.services;

import com.expensemanagement.models.UserModel;
import com.expensemanagement.models.UserRole;
import com.expensemanagement.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Autowired
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers() {
        UserModel user = new UserModel(
                "Joao",
                "Silva",
                "joao@gmail.com",
                "12345678",
                "05459460859",
                new BigDecimal(100),
                UserRole.USER
        );

        List<UserModel> usersList = new ArrayList<>();
        usersList.add(user);

        when(this.userRepository.findAll()).thenReturn(usersList);
        List<UserModel> users = this.userService.getUsers();

        Assertions.assertEquals(usersList.size(), users.size());
        Assertions.assertEquals(user.getId(), users.get(0).getId());
    }
}