package lth.com.hrm.HumanManagement.Service.Department;

public interface DepartmentService {
    List<Department> getAll ();

    Department create(Department department);

    Long update(Department department);

    Department getById(Long id);
    void delete(Long id);
}