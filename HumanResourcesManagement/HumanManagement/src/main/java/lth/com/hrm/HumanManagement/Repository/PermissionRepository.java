package lth.com.hrm.HumanManagement.Repository;

import lth.com.hrm.HumanManagement.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Permission findByName(String permission);

}
