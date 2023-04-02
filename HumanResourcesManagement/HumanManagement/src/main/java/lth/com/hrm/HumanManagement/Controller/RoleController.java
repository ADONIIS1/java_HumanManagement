package lth.com.hrm.HumanManagement.Controller;

import lombok.RequiredArgsConstructor;
import lth.com.hrm.HumanManagement.Auth.ApiResponse;
import lth.com.hrm.HumanManagement.Entity.Permission;
import lth.com.hrm.HumanManagement.Entity.Role;
import lth.com.hrm.HumanManagement.Repository.RoleCustomRepo;
import lth.com.hrm.HumanManagement.RequestBodyValid.RequestBodyData;
import lth.com.hrm.HumanManagement.Service.Role.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    RoleCustomRepo roleCustomRepo;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll (){
        return ResponseEntity.status(200).body(
                new ApiResponse(200,roleService.getAll())
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create (@RequestBody Role role){
        return ResponseEntity.status(201).body(
                new ApiResponse(201,roleService.create(role))
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> update (@org.springframework.web.bind.annotation.RequestBody Role role){
        if(roleService.getById(role.getId()) == null){
            return ResponseEntity.status(404).body(
                    new ApiResponse(404,"User not found",null)
            );
        }
        roleService.update(role);
        return ResponseEntity.status(200).body(
                new ApiResponse(200,"Update Success Fully",role)
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> delete (@org.springframework.web.bind.annotation.RequestBody Long id){
        if(roleService.getById(id) == null){
            return ResponseEntity.status(404).body(
                    new ApiResponse(404,"User Not Found")
            );
        }
        roleService.delete(id);
        return ResponseEntity.status(200).body(
                new ApiResponse(200,"Delete Success Fully")
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/addPermissionstoRole/{roleId}")
    public ResponseEntity<ApiResponse> addRolestoUser (@PathVariable Long roleId, @RequestBody RequestBodyData<Permission> permissions){
        System.out.println("Input : " + roleId);
        System.out.println("Input : " + permissions.getData().size());
        roleCustomRepo.deletePemissions(roleId);
        var data = roleService.addPerrmissionstoRole(roleId,permissions.getData());
        return ResponseEntity.status(201).body(
                new ApiResponse(201,data)
        );
    }
}
