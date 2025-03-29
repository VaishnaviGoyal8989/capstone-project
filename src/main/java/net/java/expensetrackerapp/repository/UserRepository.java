package net.java.expensetrackerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.java.expensetrackerapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
