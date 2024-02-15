package com.expensemanagement.services;

import com.expensemanagement.dtos.CategoryDTO;
import com.expensemanagement.dtos.ExpenseDTO;
import com.expensemanagement.infra.security.TokenService;
import com.expensemanagement.models.ExpenseCategory;
import com.expensemanagement.models.ExpenseModel;
import com.expensemanagement.models.UserModel;
import com.expensemanagement.repositories.ExpenseCategoryRepository;
import com.expensemanagement.repositories.ExpenseRepository;
import com.expensemanagement.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private TokenService tokenService;

    public List<ExpenseModel> getAllExpenses() {
        return this.expenseRepository.findAll();
    }

    public ExpenseModel createExpense(ExpenseDTO expenseDTO, HttpServletRequest request) throws Exception {
        UserDetails user = this.recoverLoggedUser(request);
        UserModel userModel = (UserModel) this.userRepository.findByEmail(user.getUsername());

        if(expenseDTO == null) {
            throw new Exception("Something went wrong");
        }

        if(userModel.getBalance().compareTo(expenseDTO.amount()) < 0) {
            throw new Exception("The expense is higher than the user balance");
        }


        ExpenseModel expense =  populateAndSaveExpense(expenseDTO, user);
        updateUserBalance(expenseDTO, user);

        return expense;
    }

    public ExpenseCategory createCategory(CategoryDTO categoryDTO) throws Exception {
        if(categoryDTO == null) {
            throw new Exception("Category cannot be null");
        }

        ExpenseCategory category = new ExpenseCategory();
        category.setCategory(categoryDTO.category());

        return this.expenseCategoryRepository.save(category);
    }

    public List<ExpenseCategory> getAllCategories() {
        return this.expenseCategoryRepository.findAll();
    }

    public ExpenseModel populateAndSaveExpense(ExpenseDTO expenseDTO, UserDetails user) throws Exception {
        ExpenseModel expenseModel = new ExpenseModel();
        expenseModel.setExpense(expenseDTO.expense());
        expenseModel.setAmount(expenseDTO.amount());
        expenseModel.setDescription(expenseDTO.description());

        Optional<ExpenseCategory> category = this.expenseCategoryRepository.findById(expenseDTO.category_id());
        expenseModel.setCategory(category.orElse(null));

        expenseModel.setTimestamp(LocalDateTime.now());
        expenseModel.setUser((UserModel) user);

        return this.expenseRepository.save(expenseModel);
    }

    private void updateUserBalance(ExpenseDTO expenseDTO, UserDetails user) throws Exception {
        UserModel userModel = (UserModel) this.userRepository.findByEmail(user.getUsername());
        userModel.setBalance(userModel.getBalance().subtract(expenseDTO.amount()));

        this.userRepository.save(userModel);
    }

    private UserDetails recoverLoggedUser(HttpServletRequest request) throws Exception {
        var token = this.recoverToken(request);
        if(token != null) {
            var login = tokenService.validateToken(token);
            return userRepository.findByEmail(login);
        }
        throw new Exception("Token cannot be null");
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}
