package lth.com.hrm.HumanManagement.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lth.com.hrm.HumanManagement.Auth.ApiResponse;
import lth.com.hrm.HumanManagement.Auth.AuthenticationRequest;
import lth.com.hrm.HumanManagement.Auth.AuthenticationResponse;
import lth.com.hrm.HumanManagement.Entity.User;
import lth.com.hrm.HumanManagement.Repository.RoleCustomRepo;
import lth.com.hrm.HumanManagement.Service.Auth.AuthenticationService;
import lth.com.hrm.HumanManagement.Service.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lth.com.hrm.HumanManagement.Entity.Permission;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    private final RoleCustomRepo roleCustomRepo;

    public static final String Secret_key = "123";

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login") // Route Login
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getCurrentUser") // Route Login
    public ResponseEntity<ApiResponse> getCurrentUser(@RequestBody User user){
        return ResponseEntity.ok().body(new ApiResponse(200,userService.getById(user.getId())));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/checkAuth") // Route Login
    public ResponseEntity<String> checkAuth(){
        return ResponseEntity.ok("Authentication SuccessFully");
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping ("/refreshtoken") // Route Login
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null || authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String Email = decodedJWT.getSubject();
                var permissions  = roleCustomRepo.getPermissionByUser(Email);
                String Access_Token = JWT.create()
                        .withSubject(Email)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 50 *60 *1000)) // 30 *1000
                        .withClaim("roles",permissions.stream().map(Permission::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                String Refresh_Token = JWT.create()
                        .withSubject(Email)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 50 *60 *1000)) // 30 *1000
                        .sign(algorithm);
                Map<String, String> token_refreshToken = new HashMap<>();

                token_refreshToken.put("token", Access_Token);
                token_refreshToken.put("refreshtoken", Refresh_Token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), token_refreshToken);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(UNAUTHORIZED.value());
                Map<String, String> error = new HashMap<>();
                error.put("message", "Refreshtoken đã hết hạn sử dụng");
                error.put("status", "401");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
    }
}
