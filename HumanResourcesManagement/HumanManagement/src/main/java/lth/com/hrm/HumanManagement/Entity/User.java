package lth.com.hrm.HumanManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
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
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
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
    private String address;
    private String phone;

    private Date birthDay;

    private String avatar;

    private boolean active;

    public User(Long id, String fullName, String userName, String password, String email, String address, Date birthDay, String avatar, boolean active, Set<Role> roles, Salary salary, Degree degree, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.birthDay = birthDay;
        this.avatar = avatar;
        this.active = active;
        this.roles = roles;
        this.salary = salary;
        this.degree = degree;
        this.department = department;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "Users_ID"),
            inverseJoinColumns = @JoinColumn(name = "Roles_ID")
    )
    @JsonIdentityReference(alwaysAsId = true)
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