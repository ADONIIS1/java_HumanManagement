package lth.com.hrm.HumanManagement.EntityManager;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "degrees", schema = "testauth", catalog = "")
public class DegreesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "majors")
    private String majors;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "specialized")
    private String specialized;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreesEntity that = (DegreesEntity) o;
        return id == that.id && Objects.equals(majors, that.majors) && Objects.equals(name, that.name) && Objects.equals(specialized, that.specialized);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majors, name, specialized);
    }
}
