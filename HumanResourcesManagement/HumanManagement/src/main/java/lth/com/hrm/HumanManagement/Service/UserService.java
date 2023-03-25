package lth.com.hrm.HumanManagement.Service;

public class UserService {
    Long saveUser(User user);
    Long saveRole(Role role);
    Long savePermission(Permission permission);

    void addtoUser(String userName,String roleName);
    void addRoletoPermission(String RoleName,String PermissionName);
    void DeleteRoleByUser(Long id);
}
