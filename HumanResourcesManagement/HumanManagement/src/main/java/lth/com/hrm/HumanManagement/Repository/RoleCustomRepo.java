package lth.com.hrm.HumanManagement.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lth.com.hrm.HumanManagement.Entity.Permission;
import lth.com.hrm.HumanManagement.Entity.Role;
import lth.com.hrm.HumanManagement.Entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;
    public List<Role> getRole(User user) {
        Session session = entityManager.unwrap( Session.class );
        String a = "select r.* from users u join users_role ur on u.id = ur.users_id join roles r on r.id = ur.roles_id Where u.email = :Email";
        List<Role> persons = session.createNativeQuery(a).addEntity(Role.class)
                .setParameter("Email",user.getEmail())
                .list();
        return persons;
    }
    public List<Permission> getPermission(Role role) {
        Session session = entityManager.unwrap( Session.class );
        String a = "select p.* from permissions p join roles_permission rp on p.id = rp.permissions_id join roles r on r.id = rp.roles_id Where r.name = :NameRole";
        List<Permission> permissions = session.createNativeQuery(a).addEntity(Permission.class)
                .setParameter("NameRole",role.getName())
                .list();
        return permissions;
    }
    public List<Permission> getPermissionByUser(String Email) {
        Session session = entityManager.unwrap( Session.class );
        String query =
                "select p.* from users u join users_role ur on u.id = ur.users_id join roles r on ur.roles_id = r.id join roles_permission rp on r.id = rp.roles_id join permissions p on rp.permissions_id = p.id where u.email = :user_Email";
        List<Permission> permissions = session.createNativeQuery(query).addEntity(Permission.class)
                .setParameter("user_Email",Email)
                .list();
        return permissions;
    }
    public void deleteRoles(Long userId){
        var entityManagerFactory =  Persistence.createEntityManagerFactory("default2");
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM UsersRoleEntity e WHERE e.usersId = :user_id ");
            query.setParameter("user_id", userId);
            int rowsDeleted = query.executeUpdate();
            System.out.println("entities deleted: " + rowsDeleted);
            em.getTransaction().commit();
        }
        catch (Exception err){
            em.getTransaction().rollback();
            System.out.println(err);
        }
        em.close();
    }
    public void deletePemission(Long roleId){
        var entityManagerFactory =  Persistence.createEntityManagerFactory("default2");
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM RolesPermissionEntity e WHERE e.rolesId = :role_id ");
            query.setParameter("role_id", roleId);
            int rowsDeleted = query.executeUpdate();
            System.out.println("entities deleted: " + rowsDeleted);
            em.getTransaction().commit();
        }
        catch (Exception err){
            em.getTransaction().rollback();
            System.out.println(err);
        }
        em.close();
    }
    public void addPemissiontoRole(Long roleId,Long permissionId){
        var entityManagerFactory =  Persistence.createEntityManagerFactory("default2");
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("INSERT INTO RolesPermissionEntity ");
            query.setParameter("role_id", roleId);
            int rowsDeleted = query.executeUpdate();
            System.out.println("entities deleted: " + rowsDeleted);
            em.getTransaction().commit();
        }
        catch (Exception err){
            em.getTransaction().rollback();
            System.out.println(err);
        }
        em.close();
    }
}