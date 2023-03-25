@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    public final RoleCustomRepo roleCustomRepo;

    @Autowired
    private PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public Long saveRole(Role role) {
        return roleRepository.save(role).getId();
    }
    @Override
    public Long savePermission(Permission permission) {
        return permissionRepository.save(permission).getId();
    }
    @Override
    public void addtoUser(String userName, String roleName) {
        User user = userRepository.findByEmail(userName).get();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public void addRoletoPermission(String RoleName, String PermissionName) {
        Role role = roleRepository.findByName(RoleName);
        Permission permission = permissionRepository.findByName(PermissionName);
        role.getPermissions().add(permission); // add
    }
    @Override
    public void DeleteRoleByUser(Long id) {
        roleCustomRepo.deleteRoles(id);
    }

}