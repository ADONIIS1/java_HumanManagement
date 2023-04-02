package lth.com.hrm.HumanManagement.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;
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