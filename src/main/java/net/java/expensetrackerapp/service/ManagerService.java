package net.java.expensetrackerapp.service;

import net.java.expensetrackerapp.model.Expense;
import net.java.expensetrackerapp.model.ManagerExpense;
import net.java.expensetrackerapp.repository.ExpenseRepository;
import net.java.expensetrackerapp.repository.ManagerRepository;
import net.java.expensetrackerapp.repository.BudgetRepository;
import net.java.expensetrackerapp.model.BudgetDept;  // Import BudgetDept model
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private BudgetRepository budgetRepository;  //  Inject BudgetRepository

    public List<Expense> getExpensesByDepartment(String department) {
        return expenseRepository.findByDepartment(department);
    }

    public String approveExpense(Integer id) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense != null) {
            expense.setStatus("Approved");
            expenseRepository.save(expense);
            return "Expense Approved";
        }
        return "Expense Not Found";
    }

    public String rejectExpense(Integer id) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense != null) {
            expense.setStatus("Rejected");
            expenseRepository.save(expense);
            return "Expense Rejected";
        }
        return "Expense Not Found";
    }

    public String addManagerExpense(ManagerExpense manager, String managerEmail) {
        manager.setManagerEmail(managerEmail);
        manager.setStatus("Pending");

        //  Check remaining budget
        BudgetDept budget = budgetRepository.findByDepartment(manager.getDepartment());
        if (budget != null && manager.getAmount() > budget.getRemainingBudget()) {
            return "Expense amount exceeded remaining budget!";
        }

        managerRepository.save(manager);
        return "Manager Expense Added Successfully";
    }

    public List<ManagerExpense> viewExpenses(String managerEmail) {
        return managerRepository.findByManagerEmail(managerEmail);
    }

    public List<Expense> getDepartmentExpenses(String managerDept) {
        return expenseRepository.findByDepartment(managerDept);
    }
}