package lth.com.hrm.HumanManagement.Entity;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "Blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String content;
    public String title;
    public String image;
}
