package lth.com.hrm.HumanManagement.Service.Blog;

public interface BlogService {

    List<Blog> getAll();

    Blog create(Blog blog);

    Long update(Blog blog);

    Blog getById(Long id);

    void delete(Long id);

}
