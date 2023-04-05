package lth.com.hrm.HumanManagement.Repository;

import lth.com.hrm.HumanManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String Email);
    List<User> findByRoles_Name(String RoleName);
    Optional<User> findByPhone(String Phone);
    List<User> findByFullNameContainingOrEmailContaining(String FullName,String Email);
    List<User> findByFullNameContainingOrEmailContainingOrRoles_NameContaining(String FullName,String Email,String RoleName);
}