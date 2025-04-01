# EXPENSE TRACKER WEB APP

Overview : 
The Expense Tracker Web Application is designed to help organizations streamline expense tracking and approval workflows. The application enables employees to submit their expenses, managers to review and approve or reject them, and finance teams to process and finalize the transactions. With a structured approach, this system reduces errors, improves financial accountability, and enhances transparency in expense management.

```
Features: 
• Secure login & registration for Employee, Manager, and Finance Team.
• Employees add, view their expenses as well as they can delete expenses before Manager approval.
• Managers view, approve, reject employee expenses.
• Managers add expenses, reviewed by Finance Team.
• Finance Team views all expenses and allocates budgets
• Finance Team pays approved employee expenses.
• Alerts for budget exceedance to Employees & Managers

Tech Stack Used:
Frontend:
• HTML, CSS, JavaScript, Thymeleaf
Backend:
• Java 17, Spring Boot, Spring Data JPA , Spring Web , Spring Boot Dev Tools 
Database:
• PostgreSQL

System Requirements:
•Java 17
•PostgreSQL installed and running
•Maven 
•A web browser (Chrome, Firefox, Edge, etc.)
•Spring Tool Suite or Vs Code

Project Structure:

ExpenseTrackerApp [boot] [devtools]
│── src/main/java
│   │── net.java.expensetracker
│   │── net.java.expensetracker.controller
│   │   │── EmployeeController.java
│   │   │── FinanceController.java
│   │   │── ManagerController.java
│   │   │── UserController.java
│   │── net.java.expensetracker.model
│   │   │── BudgetDept.java
│   │   │── Expense.java
│   │   │── Finance.java
│   │   │── ManagerExpense.java
│   │   │── User.java
│   │── net.java.expensetracker.repository
│   │   │── BudgetRepository.java
│   │   │── ExpenseRepository.java
│   │   │── FinanceRepository.java
│   │   │── ManagerRepository.java
│   │   │── UserRepository.java
│   │── net.java.expensetracker.service
│   │   │── ExpenseService.java
│   │   │── FinService.java
│   │   │── ManagerService.java
│   │   │── UserService.java
│── src/main/resources
│   │── static
│   │── templates
│   │   │── employee_dashboard.html
│   │   │── finance_dashboard.html
│   │   │── login.html
│   │   │── manager_dashboard.html
│   │   │── register.html
│   │── application.properties


API Endpoints:

User
• POST /register - Register a new user
• POST /login - Authenticate and log in a user
• GET /logout - Log out the current user
• GET /employee/dashboard - View employee dashboard
• GET /manager/dashboard - View manager dashboard
• GET /finance/dashboard - View finance dashboard
Employee
• POST /employee/add-expense - Submit a new expense
• GET /employee/expenses - View all submitted expenses
• DELETE /employee/delete-expense/{id} - Delete an expense by ID
Manager
• GET /manager/ownExpenses - View manager's own expenses
• PUT /manager/approve/{id} - Approve an expense by ID
• PUT /manager/reject/{id} - Reject an expense by ID
• POST /manager/addExpense - Submit a new expense
• GET /manager/departmentExpenses - View all department expenses
Finance Team:
• GET /financeTeam/employeeExpenses - View all employee expenses
• GET /financeTeam/managerExpenses - View all manager expenses
• POST /financeTeam/updateManagerExpenseStatus/{id} - Update mananager expense status
• POST /financeTeam/updateEmployeeExpenseStatus/{id} - Update employee expense status
• GET /financeTeam/budgets - View all budgets
• POST /financeTeam/addBudget - Add a new budget

Workflow:
1.User Authentication:
• Users register & log in with company email & password.
• Redirected to role-based dashboards (Employee, Manager, Finance Team).
2.Employee:
• Add expenses with name, amount, type, and date.
• Status set as "Pending" on submission.
• Delete expenses before Manager approval/rejection.
3.Manager
• View employee expenses within their department.
• Approve or reject employee expenses.
• Add expenses, initially marked as "Pending".
• Finance Team updates status to "Paid" or "Rejected".
4.Finance Team
• View all expenses from Employees & Managers.
• Allocate budgets to departments.
• Mark approved employee expenses as Paid.
• Approve or reject Manager-added expenses.
• Deduct paid expenses from the department’s budget.
5. Budget Constraints and Restrictions
• Prevents adding expenses if the amount exceeds the department’s budget.
• Triggers alert when an expense exceeds the budget limit

Future Scope:
•AI-based expense analysis
•Mobile app integration
•Automated bill payments
•Multi-currency support
```
