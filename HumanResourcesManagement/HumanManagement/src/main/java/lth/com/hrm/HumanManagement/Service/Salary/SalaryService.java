package lth.com.hrm.HumanManagement.Service.Salary;

import lth.com.hrm.HumanManagement.Entity.Salary;

import java.util.List;

public interface SalaryService {
    List<Salary> getAll ();

    Salary create(Salary salary);

    Long update(Salary salary);

    Salary getById(Long id);
    void delete(Long id);
}
