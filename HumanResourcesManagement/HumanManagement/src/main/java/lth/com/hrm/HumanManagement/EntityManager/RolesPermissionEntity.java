package lth.com.hrm.HumanManagement.EntityManager;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles_permission", schema = "testauth", catalog = "")
@IdClass(RolesPermissionEntityPK.class)
public class RolesPermissionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roles_id")
    private long rolesId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permissions_id")
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
        RolesPermissionEntity that = (RolesPermissionEntity) o;
        return rolesId == that.rolesId && permissionsId == that.permissionsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolesId, permissionsId);
    }
}
