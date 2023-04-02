package lth.com.hrm.HumanManagement.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findById(Long id);

}
