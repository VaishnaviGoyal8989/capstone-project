package net.java.expensetrackerapp.repository;

import net.java.expensetrackerapp.model.BudgetDept;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BudgetRepository extends JpaRepository<BudgetDept, Integer> {
	BudgetDept findByDepartment(String department);
}