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
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    @Fetch(value = FetchMode.SELECT)
    @JsonIgnore
    private Set<User> users = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "roles_permission",
            joinColumns = @JoinColumn(name = "Roles_ID"),
            inverseJoinColumns = @JoinColumn(name = "Permissions_ID")
    )
    private Set<Permission> permissions = new HashSet<>();
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(Long id, String name, Set<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role(String name) {
        this.name = name;
    }
}
