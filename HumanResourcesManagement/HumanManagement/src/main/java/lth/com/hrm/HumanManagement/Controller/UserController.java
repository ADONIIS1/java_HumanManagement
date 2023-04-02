package lth.com.hrm.HumanManagement.Controller;


import lombok.RequiredArgsConstructor;
import lth.com.hrm.HumanManagement.Auth.ApiResponse;
import lth.com.hrm.HumanManagement.Entity.Role;
import lth.com.hrm.HumanManagement.Entity.User;
import lth.com.hrm.HumanManagement.Repository.RoleCustomRepo;
import lth.com.hrm.HumanManagement.RequestBodyValid.RequestBodyData;
import lth.com.hrm.HumanManagement.Service.User.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleCustomRepo roleCustomRepo;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/GetById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll (){
        return ResponseEntity.status(200).body(
                new ApiResponse(201,userService.getlistUsers())
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create (@RequestBody User user){
        return ResponseEntity.status(201).body(
                new ApiResponse(201,userService.save(user))
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> update (@RequestBody User user){
        return null;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> delete (@RequestBody Long id){
        userService.delete(id);
        return ResponseEntity.status(201).body(
                new ApiResponse(201,"Delete Success Fully")
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/addRolestoUser/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addRolestoUser (@PathVariable Long userId, @RequestBody RequestBodyData<Role> roles){
        System.out.println("Input : " + userId);
        System.out.println("Input : " + roles.getData().size());
        roleCustomRepo.deleteRoles(userId);
        var data = userService.addRolestoUser(userId,roles.getData());
        return ResponseEntity.status(201).body(
                new ApiResponse(201,data)
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/getAllByRoleName")
    public ResponseEntity<ApiResponse> addRolestoUser (@RequestBody RequestBodyData<User> user){
        System.out.println("Input : " + user.getItem());
        var data = userService.findAllByRoles("ADMIN");
        var dataCheck = userService.findAllByFullNameOrEmail(
                user.getItem().getFullName(),user.getItem().getEmail());

        return ResponseEntity.status(201).body(
                new ApiResponse(201,data)
        );
    }
}
