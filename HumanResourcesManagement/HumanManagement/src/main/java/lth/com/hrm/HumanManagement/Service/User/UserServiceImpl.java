package lth.com.hrm.HumanManagement.Service.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lth.com.hrm.HumanManagement.Entity.Permission;
import lth.com.hrm.HumanManagement.Entity.Role;
import lth.com.hrm.HumanManagement.Entity.User;
import lth.com.hrm.HumanManagement.Repository.PermissionRepository;
import lth.com.hrm.HumanManagement.Repository.RoleCustomRepo;
import lth.com.hrm.HumanManagement.Repository.RoleRepository;
import lth.com.hrm.HumanManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    public final RoleCustomRepo roleCustomRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getlistUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        var checkUser = getById(user.getId());
        if (checkUser != null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        var checkUser = getById(id);
        if (checkUser != null) {
            userRepository.deleteById(id);
        }
    }

    /*    @Override
        public Long saveRole(Role role) {
            return roleRepository.save(role).getId();
        }
        @Override
        public Long savePermission(Permission permission) {
            return permissionRepository.save(permission).getId();
        }
        @Override
        public void addRoletoUser(String userName, String roleName) {
            User user = userRepository.findByEmail(userName).get();
            Role role = roleRepository.findByName(roleName);
            user.getRoles().add(role);

        }
        @Override
        public void addPermissiontoRole(String RoleName, String PermissionName) {
            Role role = roleRepository.findByName(RoleName);
            Permission permission = permissionRepository.findByName(PermissionName);
            role.getPermissions().add(permission); // add
        }*/
    @Override
    public User addRolestoUser(Long userId, List<Role> roles) {
        var user = this.getById(userId);
        if (user == null) {
            return null;
        }
        System.out.println("Check roles " + roles);
        roleCustomRepo.deleteRoles(userId);
        roles.stream().forEach(p -> {
            user.getRoles().add(p);
        });
        return this.getById(userId);
    }

    @Override
    public List<User> findAllByFullNameOrEmail(String fullName,String Email) {
        return userRepository.findByFullNameContainingOrEmailContaining
                (fullName,Email);
    }
    @Override
    public User findByPhone(String Phone) {
        if(userRepository.findByPhone(Phone).get() != null)
            return userRepository.findByPhone(Phone).get();
        return null;
    }

    @Override
    public User findByName(String Email) {
        if(userRepository.findByEmail(Email).get() != null)
            return userRepository.findByEmail(Email).get();
        return null;
    }

    @Override
    public List<User> findAllByRoles(String RoleName) {
        var data = userRepository.findByFullNameContainingOrEmailContainingOrRoles_NameContaining("a","b",RoleName);
        System.out.println("Test"+ data);
        return userRepository.findByRoles_Name(RoleName);
    }

/*    @Override
    public User addPermissionstoRole(Long roleId,List<Permission> permissions) {
        return null;
    }

    @Override
    public void DeleteRoleByUser(Long id) {
        roleCustomRepo.deleteRoles(id);
    }*/
}
