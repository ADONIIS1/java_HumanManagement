package lth.com.hrm.HumanManagement.EntityManager;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class UsersRoleEntityPK implements Serializable {
    @Column(name = "users_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usersId;
    @Column(name = "roles_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        UsersRoleEntityPK that = (UsersRoleEntityPK) o;
        return usersId == that.usersId && rolesId == that.rolesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, rolesId);
    }
}
