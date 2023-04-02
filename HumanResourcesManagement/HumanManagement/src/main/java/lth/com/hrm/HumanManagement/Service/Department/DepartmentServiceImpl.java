package lth.com.hrm.HumanManagement.Service.Department;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lth.com.hrm.HumanManagement.Entity.Department;
import lth.com.hrm.HumanManagement.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department create(Department degree) {
        return departmentRepository.save(degree);
    }

    @Override
    public Long update(Department req) {
        Optional<Department> degree = departmentRepository.findById(req.getId());

        if(degree != null){
            Long blogId = departmentRepository.save(req).getId();
            return blogId;
        }
        return null;
    }

    @Override
    public Department getById(Long id) {
        Optional<Department> degree = departmentRepository.findById(id);
        if(degree != null){
            return degree.get();
        }
        return null;
    }
    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}