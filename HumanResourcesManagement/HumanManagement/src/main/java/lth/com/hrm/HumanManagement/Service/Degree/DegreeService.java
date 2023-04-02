package lth.com.hrm.HumanManagement.Service.Degree;

import lth.com.hrm.HumanManagement.Entity.Degree;

import java.util.List;

public interface DegreeService {
    List<Degree> getAll();

    Degree create(Degree degree);

    Long update(Degree degree);

    Degree getById(Long id);

    void delete(Long id);
}