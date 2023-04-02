package lth.com.hrm.HumanManagement.Entity;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public double basic_salary;
    public int hsl;
    @OneToMany(mappedBy = "salary", fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<User> users = new HashSet<>();
}