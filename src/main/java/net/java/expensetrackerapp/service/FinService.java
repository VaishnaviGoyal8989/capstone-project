package net.java.expensetrackerapp.service;

import net.java.expensetrackerapp.model.Expense;
import net.java.expensetrackerapp.model.ManagerExpense;
import net.java.expensetrackerapp.model.BudgetDept;
import net.java.expensetrackerapp.repository.ExpenseRepository;
import net.java.expensetrackerapp.repository.ManagerRepository;
import net.java.expensetrackerapp.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private BudgetRepository budgetRepository;

    public List<BudgetDept> getAllBudgets() {
        return budgetRepository.findAll();  
    }

    public void addBudget(BudgetDept budgetDept) {
        budgetDept.setRemainingBudget(budgetDept.getAmount());
        budgetRepository.save(budgetDept);
    }


    //  Fetch all employee expenses
    public List<Expense> getAllEmployeeExpenses() {
        return expenseRepository.findAll();
    }

    //  Fetch all manager expenses
    public List<ManagerExpense> getAllManagerExpenses() {
        return managerRepository.findAll();
    }
    public void updateManagerExpenseStatus(Integer id, String status) {
        ManagerExpense expense = managerRepository.findById(id).orElse(null);
        if (expense != null) {
            expense.setStatus(status);
            managerRepository.save(expense);
            if ("Paid".equalsIgnoreCase(status)) {
                deductFromDepartmentBudget(expense.getDepartment(), expense.getAmount());
            }
        }
    }
    public void updateEmployeeExpenseStatus(Integer id, String status) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense != null && "Approved".equalsIgnoreCase(expense.getStatus())) {
            expense.setStatus(status);
            expenseRepository.save(expense);
            if ("Paid".equalsIgnoreCase(status)) {
                deductFromDepartmentBudget(expense.getDepartment(), expense.getAmount());
            }
        }
    }
 //  Deduct Expense Amount from Remaining Budget
    private void deductFromDepartmentBudget(String department, Double amount) {
        BudgetDept budget = budgetRepository.findByDepartment(department);
        if (budget != null && budget.getRemainingBudget() >= amount) {
            budget.setRemainingBudget(budget.getRemainingBudget() - amount);
            budgetRepository.save(budget);
        }
    }
    


}