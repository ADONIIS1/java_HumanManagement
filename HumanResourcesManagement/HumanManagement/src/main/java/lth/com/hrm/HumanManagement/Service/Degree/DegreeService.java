package lth.com.hrm.HumanManagement.Service.Degree;

public interface DegreeService {
    List<Degree> getAll();

    Degree create(Degree blog);

    Long update(Degree blog);

    Degree getById(Long id);

    void delete(Long id);
}