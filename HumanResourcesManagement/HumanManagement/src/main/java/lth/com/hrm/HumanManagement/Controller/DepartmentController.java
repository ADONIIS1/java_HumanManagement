@RestController
@RequestMapping("/department")
@RequiredArgsConstructor

public class DepartmentController {
    private final DepartmentService departmentService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll (){
        return ResponseEntity.status(200).body(
                new ApiResponse(201,departmentService.getAll())
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create (@RequestBody Department department){
        return ResponseEntity.status(201).body(
                new ApiResponse(201,departmentService.create(department))
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> update (@RequestBody Department department){
        if(departmentService.getById(department.getId()) == null){
            return ResponseEntity.status(404).body(
                    new ApiResponse(404,"User not found",null)
            );
        }
        departmentService.update(department);
        return ResponseEntity.status(200).body(
                new ApiResponse(200,"Update Success Fully",department)
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> delete (@RequestBody Long id){
        if(departmentService.getById(id) == null){
            return ResponseEntity.status(404).body(
                    new ApiResponse("User Not Found")
            );
        }
        departmentService.delete(id);
        return ResponseEntity.status(200).body(
                new ApiResponse("Delete Success Fully")
        );
    }
}