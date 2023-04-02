package lth.com.hrm.HumanManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar")
    private String fullName;
    private String userName;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "Users_ID"),
            inverseJoinColumns = @JoinColumn(name = "Roles_ID")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salary_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Salary salary ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "degree_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Degree degree ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department ;
    public User(Long id, String fullname, String userName, String password, String email, Set<Role> roles) {
        this.id = id;
        this.fullName = fullname;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    @Transient
    public String RoleName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(item -> authorities.add(new SimpleGrantedAuthority(item.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}