package lth.com.hrm.HumanManagement.Entity;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "Degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String specialized;
    public String majors;
    @OneToMany(mappedBy = "degree", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
}
