package lth.com.hrm.HumanManagement.Repository;

import lth.com.hrm.HumanManagement.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findById(Long id);

}
