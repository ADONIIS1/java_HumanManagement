package lth.com.hrm.HumanManagement.Service.Role;


import lth.com.hrm.HumanManagement.Entity.Permission;
import lth.com.hrm.HumanManagement.Entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll ();

    Role create(Role role);

    Long update(Role role);

    Role getById(Long id);

    Role addPerrmissionstoRole(Long roleId, List<Permission> permissions);
    void delete(Long id);
}
