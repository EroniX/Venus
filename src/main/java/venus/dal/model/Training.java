package venus.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Training extends BaseEntity {

    @Column(nullable = false)
    private String Name;

    @OneToMany(mappedBy="training", cascade={CascadeType.ALL})
    private List<Subject> subjects;

    @JoinTable(
            name = "users_trainings",
            joinColumns = @JoinColumn(name = "trainingid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> students;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
