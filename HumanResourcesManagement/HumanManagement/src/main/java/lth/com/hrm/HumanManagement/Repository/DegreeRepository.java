package lth.com.hrm.HumanManagement.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    Optional<Degree> findById(Long id);
}
