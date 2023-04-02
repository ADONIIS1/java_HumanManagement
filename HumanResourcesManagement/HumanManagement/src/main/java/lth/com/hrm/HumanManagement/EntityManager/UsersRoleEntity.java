package lth.com.hrm.HumanManagement.EntityManager;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_role", schema = "testauth", catalog = "")
@IdClass(UsersRoleEntityPK.class)
public class UsersRoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "users_id")
    private long usersId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roles_id")
    private long rolesId;

    public long getUsersId() {
        return usersId;
    }

    public void setUsersId(long usersId) {
        this.usersId = usersId;
    }

    public long getRolesId() {
        return rolesId;
    }

    public void setRolesId(long rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRoleEntity that = (UsersRoleEntity) o;
        return usersId == that.usersId && rolesId == that.rolesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, rolesId);
    }
}
