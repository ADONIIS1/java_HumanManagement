package lth.com.hrm.HumanManagement.Service.Salary;

public interface SalaryService {
    List<Salary> getAll ();

    Salary create(Salary salary);

    Long update(Salary salary);

    Salary getById(Long id);
    void delete(Long id);
}
