package net.java.expensetrackerapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "budget_dept")
public class BudgetDept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String department;
    private Double amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double remainingBudget;

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getRemainingBudget() { return remainingBudget; }
    public void setRemainingBudget(Double remainingBudget) { this.remainingBudget = remainingBudget; }
}
