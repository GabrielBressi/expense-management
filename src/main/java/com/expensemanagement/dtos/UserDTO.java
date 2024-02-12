package com.expensemanagement.dtos;


import com.expensemanagement.models.UserRole;

import java.math.BigDecimal;


public record UserDTO(String firstName, String lastName, String email, String password, String document, BigDecimal balance, UserRole role) {

}
