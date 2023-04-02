package lth.com.hrm.HumanManagement.Service.Blog;

import lth.com.hrm.HumanManagement.Entity.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAll();

    Blog create(Blog blog);

    Long update(Blog blog);

    Blog getById(Long id);

    void delete(Long id);

}
