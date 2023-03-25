package lth.com.hrm.HumanManagement.Repository;

import lth.com.hrm.HumanManagement.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String role);
}
