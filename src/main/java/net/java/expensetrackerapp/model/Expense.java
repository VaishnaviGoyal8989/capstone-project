package net.java.expensetrackerapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String employeeName;
    private String expenseName;
    private String department;
    private String expenseType;
    private String date;
    private String description;
    private Double amount;
    private String status;
    private String receipt;
    private String submittedBy; 
    private String employeeEmail;

    // Getters & Setters  
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getExpenseName() { return expenseName; }
    public void setExpenseName(String expenseName) { this.expenseName = expenseName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getExpenseType() { return expenseType; }
    public void setExpenseType(String expenseType) { this.expenseType = expenseType; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReceipt() { return receipt; }
    public void setReceipt(String receipt) { this.receipt = receipt; }

    public String getSubmittedBy() { return submittedBy; }
    public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }


    
    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }
}
