package lth.com.hrm.HumanManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

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