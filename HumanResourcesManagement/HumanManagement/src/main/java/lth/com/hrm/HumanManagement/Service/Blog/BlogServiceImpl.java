package lth.com.hrm.HumanManagement.Service.Blog;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lth.com.hrm.HumanManagement.Entity.Blog;
import lth.com.hrm.HumanManagement.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog create(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Long update(Blog req) {

        Optional<Blog> blog = blogRepository.findById(req.getId());

        if (blog != null) {
            Long blogId = blogRepository.save(req).getId();
            return blogId;
        }
        return null;
    }

    @Override
    public Blog getById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog != null) {
            return blog.get();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}