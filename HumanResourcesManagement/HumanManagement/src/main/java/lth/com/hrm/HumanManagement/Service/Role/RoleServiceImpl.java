package lth.com.hrm.HumanManagement.Service.Role;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lth.com.hrm.HumanManagement.Entity.Permission;
import lth.com.hrm.HumanManagement.Entity.Role;
import lth.com.hrm.HumanManagement.Repository.PermissionRepository;
import lth.com.hrm.HumanManagement.Repository.RoleCustomRepo;
import lth.com.hrm.HumanManagement.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    RoleCustomRepo roleCustomRepo;
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role create(Role role) {
        role.setName(role.getName().toUpperCase());
        List<Permission> permissions = Arrays.asList(
                new Permission(role.getName()),
                new Permission(role.getName() + ".VIEW"),
                new Permission(role.getName() + ".CREATE"),
                new Permission(role.getName() + ".UPDATE"),
                new Permission(role.getName() + ".DELETE")
        );
        //permissions.stream().filter(p -> p.getName().equals());
        var roleCreate = roleRepository.save(role);
        permissionRepository.saveAll(permissions);
        return roleCreate;
    }

    @Override
    public Long update(Role req) {
        Optional<Role> degree = roleRepository.findById(req.getId());

        if (degree != null) {
            Long blogId = roleRepository.save(req).getId();
            return blogId;
        }
        return null;
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> degree = roleRepository.findById(id);
        if (degree != null) {
            return degree.get();
        }
        return null;
    }

    @Override
    public Role addPerrmissionstoRole(Long roleId, List<Permission> permissions) {
        var role = this.getById(roleId);
        if (role == null) {
            return null;
        }
        roleCustomRepo.deleteRoles(roleId); // Delete List<Permission> in Role
        role.getPermissions().addAll(permissions); // Add List <Permission> to Role
        return this.getById(roleId);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
