@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll (){
        return ResponseEntity.status(200).body(
                new ApiResponse(201,salaryService.getAll())
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create (@RequestBody Salary salary){
        return ResponseEntity.status(201).body(
                new ApiResponse(201,salaryService.create(salary))
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> update (@RequestBody Salary salary){
        if(salaryService.getById(salary.getId()) == null){
            return ResponseEntity.status(404).body(
                    new ApiResponse(404,"User not found",null)
            );
        }
        salaryService.update(salary);
        return ResponseEntity.status(200).body(
                new ApiResponse(200,"Update Success Fully",salary)
        );
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> delete (@RequestBody Long id){
        if(salaryService.getById(id) == null){
            return ResponseEntity.status(404).body(
                    new ApiResponse("User Not Found")
            );
        }
        salaryService.delete(id);
        return ResponseEntity.status(200).body(
                new ApiResponse("Delete Success Fully")
        );
    }
}