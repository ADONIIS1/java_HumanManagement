package lth.com.hrm.HumanManagement.EntityManager;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "salary", schema = "testauth", catalog = "")
public class SalaryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "hsl")
    private int hsl;
    @Basic
    @Column(name = "basic_salary")
    private double basicSalary;
    @Basic
    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHsl() {
        return hsl;
    }

    public void setHsl(int hsl) {
        this.hsl = hsl;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryEntity that = (SalaryEntity) o;
        return id == that.id && hsl == that.hsl && Double.compare(that.basicSalary, basicSalary) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hsl, basicSalary, name);
    }
}
