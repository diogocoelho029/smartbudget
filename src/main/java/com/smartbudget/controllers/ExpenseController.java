package com.smartbudget.controllers;

import com.smartbudget.model.Expense;
import com.smartbudget.model.User;
import com.smartbudget.repositories.ExpenseRepository;
import com.smartbudget.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<Expense> getExpenses(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return expenseRepository.findByUser(user);
    }

    @PostMapping("/{userId}")
    public Expense createExpense(@PathVariable Long userId, @RequestBody Expense expense){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    @PutMapping("/{expenseId}")
    public Expense updateExpense(@PathVariable Long expenseId, @RequestBody Expense updatedExpense){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setDescription(updatedExpense.getDescription());
        expense.setAmount(updatedExpense.getAmount());
        expense.setCategory(updatedExpense.getCategory());
        expense.setStartDate(updatedExpense.getStartDate());
        expense.setEndDate(updatedExpense.getEndDate());
        expense.setType(updatedExpense.getType());
        expense.setPaid(updatedExpense.getPaid());
        return expenseRepository.save(expense);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId){
        expenseRepository.deleteById(expenseId);
    }
}
