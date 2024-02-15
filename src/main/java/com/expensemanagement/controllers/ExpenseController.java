package com.expensemanagement.controllers;

import com.expensemanagement.dtos.CategoryDTO;
import com.expensemanagement.dtos.ExpenseDTO;
import com.expensemanagement.models.ExpenseCategory;
import com.expensemanagement.models.ExpenseModel;
import com.expensemanagement.services.ExpenseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseModel>> getAllExpenses() {
        List<ExpenseModel> expenses = this.expenseService.getAllExpenses();

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpenseModel> createExpense(@RequestBody ExpenseDTO expenseDTO, HttpServletRequest request) throws Exception {
        ExpenseModel expense = expenseService.createExpense(expenseDTO, request);

        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @PostMapping("/category")
    public ResponseEntity<ExpenseCategory> createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        ExpenseCategory category = expenseService.createCategory(categoryDTO);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ExpenseCategory>> getCategories() throws Exception {
        List<ExpenseCategory> categories = expenseService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
}
