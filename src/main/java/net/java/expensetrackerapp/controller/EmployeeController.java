package net.java.expensetrackerapp.controller;

import jakarta.servlet.http.HttpSession;
import net.java.expensetrackerapp.model.Expense;
import net.java.expensetrackerapp.model.User;
import net.java.expensetrackerapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add-expense")
    public String addExpense(@RequestBody Expense expense, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        return expenseService.addExpense(expense, user.getEmail());
    }

    @GetMapping("/expenses")
    public List<Expense> viewExpenses(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        return expenseService.viewExpenses(user.getEmail());
    }

    @DeleteMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        return expenseService.deleteExpense(id, user.getEmail());
    }
}