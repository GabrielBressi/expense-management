package com.expensemanagement.services;

import com.expensemanagement.dtos.UserDTO;
import com.expensemanagement.models.UserModel;
import com.expensemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getUsers() {
        return this.userRepository.findAll();
    }


    public UserModel createUser(UserDTO user) {
        UserModel newUser = new UserModel(
                user.firstName(),
                user.lastName(),
                user.email(),
                user.password(),
                user.document(),
                user.balance(),
                user.role()
        );

        this.userRepository.save(newUser);

        return newUser;
    }
}
