package lth.com.hrm.HumanManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

@NoArgsConstructor
@Table(name = "Roles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String display;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    private Set<User> users = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permission",
            joinColumns = @JoinColumn(name = "Roles_ID"),
            inverseJoinColumns = @JoinColumn(name = "Permissions_ID")
    )
    @JsonIdentityReference(alwaysAsId = true)
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
