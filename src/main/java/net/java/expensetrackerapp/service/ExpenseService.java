package net.java.expensetrackerapp.service;

import net.java.expensetrackerapp.model.Expense;
import net.java.expensetrackerapp.model.BudgetDept;
import net.java.expensetrackerapp.repository.ExpenseRepository;
import net.java.expensetrackerapp.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    public String addExpense(Expense expense, String employeeEmail) {

        expense.setEmployeeEmail(employeeEmail);
        expense.setStatus("Pending");
        BudgetDept budget = budgetRepository.findByDepartment(expense.getDepartment());


        if (budget != null &&  expense.getAmount() > budget.getRemainingBudget()) {
            return "Expense amount exceeded! Remaining budget: " + budget.getRemainingBudget();
        }

        
        expenseRepository.save(expense);
        return "Expense added successfully!";
    }

    public List<Expense> viewExpenses(String employeeEmail) {
        return expenseRepository.findByEmployeeEmail(employeeEmail);
    }

    public String deleteExpense(Integer id, String employeeEmail) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense != null && "Pending".equalsIgnoreCase(expense.getStatus()) 
            && employeeEmail.equals(expense.getEmployeeEmail())) {
            expenseRepository.deleteById(id);
            return "Expense deleted successfully!";
        }
        return "Only your Pending expenses can be deleted!";
    }
}
