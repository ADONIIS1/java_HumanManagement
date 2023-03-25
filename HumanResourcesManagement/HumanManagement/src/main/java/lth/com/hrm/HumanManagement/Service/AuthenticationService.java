package lth.com.hrm.HumanManagement.Service;

import lombok.RequiredArgsConstructor;
import lth.com.hrm.HumanManagement.Auth.AuthenticationRequest;
import lth.com.hrm.HumanManagement.Auth.AuthenticationResponse;
import lth.com.hrm.HumanManagement.Entity.Permission;
import lth.com.hrm.HumanManagement.Entity.Role;
import lth.com.hrm.HumanManagement.Entity.User;
import lth.com.hrm.HumanManagement.Repository.RoleCustomRepo;
import lth.com.hrm.HumanManagement.Repository.RoleRepository;
import lth.com.hrm.HumanManagement.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final RoleCustomRepo roleCustomRepo;


    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        User user = userRepository.findByEmail(authenticationRequest.getEmail()).
                orElseThrow();

        List<Role> roles =  null;
        if(user != null){
            roles = roleCustomRepo.getRole(user); // add List Role by User
        }
        // Create Authority
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // Create List Permission
        Set<Permission> setPermission = new HashSet<>();
        List<Permission> permissions =  new ArrayList<>();
        // Add to List Permission
        for(Role role : roles){
            permissions.addAll(roleCustomRepo.getPermission(role));
            // LẤY TẤT CẢ PERMISSION TỪ ROLE
        }
        // add to List Authority
        permissions.stream().forEach(item -> setPermission.add(new Permission(item.getName())));
        setPermission.stream().forEach(item -> authorities.add
                (new SimpleGrantedAuthority(item.getName())));
        var jwtAccessToken = jwtService.generateAccessToken(user,authorities);

        var jwtRefreshToken = jwtService.generateRefreshToken(user,authorities);

        return AuthenticationResponse.builder().token(jwtAccessToken).refreshToken(jwtRefreshToken).build();
    }
}