package net.java.expensetrackerapp.service;

import net.java.expensetrackerapp.model.User;
import net.java.expensetrackerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public String register(User user) {
        if (!user.getEmail().endsWith("@company.com")) {
            return "Only company email allowed!";
        }
        if (userRepo.findByEmail(user.getEmail()) != null) {
            return "Email already registered!";
        }
        userRepo.save(user);
        return "Registration Successful!";
    }

    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
