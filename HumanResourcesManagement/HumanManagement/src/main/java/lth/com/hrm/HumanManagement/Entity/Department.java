package lth.com.hrm.HumanManagement.Entity;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String address;
    public String phone;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
}