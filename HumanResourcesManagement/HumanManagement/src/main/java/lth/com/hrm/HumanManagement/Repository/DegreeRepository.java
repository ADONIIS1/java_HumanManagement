package lth.com.hrm.HumanManagement.Repository;

import lth.com.hrm.HumanManagement.Entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    Optional<Degree> findById(Long id);
}
