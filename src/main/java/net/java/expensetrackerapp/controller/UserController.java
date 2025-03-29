package net.java.expensetrackerapp.controller;

import jakarta.servlet.http.HttpSession;
import net.java.expensetrackerapp.model.User;
import net.java.expensetrackerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Show Registration Page
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Register User
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Allow only company emails
        if (!user.getEmail().endsWith("@company.com")) {
            model.addAttribute("error", "Only company email addresses are allowed!");
            return "register";
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        userRepository.save(user);
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    // Show Login Page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Login User
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        User user = userRepository.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }

        // Save user in session
        session.setAttribute("loggedUser", user);

        // Redirect based on role
        String role = user.getRole();
        if ("Employee".equalsIgnoreCase(role)) {
            return "redirect:/employee/dashboard";
        } else if ("Manager".equalsIgnoreCase(role)) {
            return "redirect:/manager/dashboard";
        } else if ("Finance Team".equalsIgnoreCase(role)) {
            return "redirect:/finance/dashboard";
        } else {
            model.addAttribute("error", "Invalid role assigned!");
            return "login";
        }
    }

    // Employee Dashboard
    @GetMapping("/employee/dashboard")
    public String employeeDashboard() {
        return "employee_dashboard";
    }

    // Manager Dashboard
    @GetMapping("/manager/dashboard")
    public String managerDashboard() {
        return "manager_dashboard";
    }

    // Finance Dashboard
    @GetMapping("/finance/dashboard")
    public String financeDashboard() {
        return "finance_dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}