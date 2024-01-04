package entity.oneToMany;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="departement")
public class Departement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dep_name")
    private String depName;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employeeList;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                '}';
    }
}
