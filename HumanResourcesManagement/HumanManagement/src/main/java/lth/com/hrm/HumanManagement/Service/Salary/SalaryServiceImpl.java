package lth.com.hrm.HumanManagement.Service.Salary;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lth.com.hrm.HumanManagement.Entity.Salary;
import lth.com.hrm.HumanManagement.Repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryRepository salaryRepository;
    @Override
    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary create(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public Long update(Salary req) {
        Optional<Salary> degree = salaryRepository.findById(req.getId());

        if(degree != null){
            Long blogId = salaryRepository.save(req).getId();
            return blogId;
        }
        return null;
    }

    @Override
    public Salary getById(Long id) {
        Optional<Salary> degree = salaryRepository.findById(id);
        if(degree != null){
            return degree.get();
        }
        return null;
    }
    @Override
    public void delete(Long id) {
        salaryRepository.deleteById(id);
    }
}