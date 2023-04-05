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
@Table(name = "Permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String display;

    @ManyToMany(mappedBy = "permissions")
    @Fetch(value = FetchMode.SELECT)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public Permission(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Permission(Long id, String name, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Permission(String name) {
        this.name = name;
    }

    public Permission(Set<Role> roles) {
        this.roles = roles;
    }
}
