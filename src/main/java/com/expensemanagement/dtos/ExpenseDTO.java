package com.expensemanagement.dtos;

import com.expensemanagement.models.ExpenseCategory;

import java.math.BigDecimal;

public record ExpenseDTO(String expense, BigDecimal amount, String description, Long category_id) {

}
