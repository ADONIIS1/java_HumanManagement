package lth.com.hrm.HumanManagement.Controller;

import lombok.RequiredArgsConstructor;
import lth.com.hrm.HumanManagement.Auth.AuthenticationRequest;
import lth.com.hrm.HumanManagement.Auth.AuthenticationResponse;
import lth.com.hrm.HumanManagement.Service.Auth.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login") // Route Login
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
