package lth.com.hrm.HumanManagement.Service.Degree;

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