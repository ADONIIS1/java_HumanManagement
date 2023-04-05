package lth.com.hrm.HumanManagement.Service.User;

import lth.com.hrm.HumanManagement.Entity.Role;
import lth.com.hrm.HumanManagement.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getlistUsers();
    User getById(Long id);
    User save(User user);
    User update(User user);
    void delete(Long id);
    User addRolestoUser(Long userId,List<Role> roles);
    List<User> findAllByRoles(String RoleName) ;
    List<User> findAllByFullNameOrEmail(String fullName,String Email);

    User findByPhone(String Phone);
    User findByName(String Email);
}
