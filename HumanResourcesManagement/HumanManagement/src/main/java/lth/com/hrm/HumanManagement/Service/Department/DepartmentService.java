package lth.com.hrm.HumanManagement.Service.Department;

import lth.com.hrm.HumanManagement.Entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll ();

    Department create(Department department);

    Long update(Department department);

    Department getById(Long id);
    void delete(Long id);
}