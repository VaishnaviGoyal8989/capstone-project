package net.java.expensetrackerapp.controller;

import jakarta.servlet.http.HttpSession;
import net.java.expensetrackerapp.model.Expense;
import net.java.expensetrackerapp.model.User;
import net.java.expensetrackerapp.model.ManagerExpense;
import net.java.expensetrackerapp.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
@CrossOrigin
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    //  Manager's own submitted expenses
    @GetMapping("/ownExpenses")
    public List<ManagerExpense> getManagerOwnExpenses(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null) {
            return managerService.viewExpenses(user.getEmail());
        }
        return null;
    }

    // Manager - Approve
    @PutMapping("/approve/{id}")
    public String approveExpense(@PathVariable Integer id) {
        return managerService.approveExpense(id);
    }

    // Manager - Reject
    @PutMapping("/reject/{id}")
    public String rejectExpense(@PathVariable Integer id) {
        return managerService.rejectExpense(id);
    }

    // Manager - Add Expense (auto assign department)
    @PostMapping("/addExpense")
    public String addManagerExpense(@RequestBody ManagerExpense managerExpense, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null) {
            // Auto set the department from logged-in manager
            managerExpense.setDepartment(user.getDepartment());
            return managerService.addManagerExpense(managerExpense, user.getEmail());
        }
        return "Manager not logged in";
    }

    //  Manager - View Department Expenses
    @GetMapping("/departmentExpenses")
    public List<Expense> getDepartmentExpenses(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null) {
            String department = user.getDepartment();
            return managerService.getDepartmentExpenses(department);
        }
        return null;
    }
}