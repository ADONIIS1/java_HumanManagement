package lth.com.hrm.HumanManagement.Service.Degree;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lth.com.hrm.HumanManagement.Entity.Degree;
import lth.com.hrm.HumanManagement.Repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DegreeServiceImpl implements DegreeService {
    @Autowired
    DegreeRepository degreeRepository;

    @Override
    public List<Degree> getAll() {
        return degreeRepository.findAll();
    }

    @Override
    public Degree create(Degree degree) {
        return degreeRepository.save(degree);
    }

    @Override
    public Long update(Degree req) {
        Optional<Degree> degree = degreeRepository.findById(req.getId());

        if (degree != null) {
            Long blogId = degreeRepository.save(req).getId();
            return blogId;
        }
        return null;
    }

    @Override
    public Degree getById(Long id) {
        Optional<Degree> degree = degreeRepository.findById(id);
        if (degree != null) {
            return degree.get();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        degreeRepository.deleteById(id);
    }
}