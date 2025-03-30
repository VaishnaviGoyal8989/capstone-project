package net.java.expensetrackerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import net.java.expensetrackerapp.model.Expense;
import net.java.expensetrackerapp.model.User;
import net.java.expensetrackerapp.service.ExpenseService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add-expense")
    public String addExpense(@RequestBody Expense expense, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null){
            return "Error: User not logged in. ";
        }
        return expenseService.addExpense(expense, user.getEmail());
    }

    @GetMapping("/expenses")
    public List<Expense> viewExpenses(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null){
            throw new RuntimeException("Error: User not logged in.");
        }
        return expenseService.viewExpenses(user.getEmail());
    }

    @DeleteMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null){
            return "Error: User not logged in. ";
        }
        return expenseService.deleteExpense(id, user.getEmail());
    }
}