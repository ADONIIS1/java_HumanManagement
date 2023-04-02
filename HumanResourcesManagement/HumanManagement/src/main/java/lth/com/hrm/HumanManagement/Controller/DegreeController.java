package lth.com.hrm.HumanManagement.Controller;

import lombok.RequiredArgsConstructor;
import lth.com.hrm.HumanManagement.Auth.ApiResponse;
import lth.com.hrm.HumanManagement.Entity.Degree;
import lth.com.hrm.HumanManagement.Service.Degree.DegreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/degree")
public class DegreeController {
    private final DegreeService degreeService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.status(200).body(
                new ApiResponse(201, degreeService.getAll()));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody Degree degree) {
        return ResponseEntity.status(201).body(
                new ApiResponse(201, degreeService.create(degree)));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody Degree degree) {
        if (degreeService.getById(degree.getId()) == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse(404, "User not found", null));
        }
        degreeService.update(degree);
        return ResponseEntity.status(200).body(
                new ApiResponse(200, "Update Success Fully", degree));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> delete(@RequestBody Long id) {
        if (degreeService.getById(id) == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse(404,"User Not Found"));
        }
        degreeService.delete(id);
        return ResponseEntity.status(200).body(
                new ApiResponse(200,"Delete Success Fully"));
    }
}
