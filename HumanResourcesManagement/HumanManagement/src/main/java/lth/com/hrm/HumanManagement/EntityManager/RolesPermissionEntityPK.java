package lth.com.hrm.HumanManagement.EntityManager;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class RolesPermissionEntityPK implements Serializable {
    @Column(name = "roles_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rolesId;
    @Column(name = "permissions_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long permissionsId;

    public long getRolesId() {
        return rolesId;
    }

    public void setRolesId(long rolesId) {
        this.rolesId = rolesId;
    }

    public long getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(long permissionsId) {
        this.permissionsId = permissionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesPermissionEntityPK that = (RolesPermissionEntityPK) o;
        return rolesId == that.rolesId && permissionsId == that.permissionsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolesId, permissionsId);
    }
}
