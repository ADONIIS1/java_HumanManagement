package lth.com.hrm.HumanManagement.Repository;

import lth.com.hrm.HumanManagement.Entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    Optional<Salary> findById(Long id);
}

