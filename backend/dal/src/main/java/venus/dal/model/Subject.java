package venus.dal.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity {

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false, unique = true)
    private String Code;

    @ManyToOne
    @JoinColumn(name = "trainingid")
    private Training training;

    @OneToMany(mappedBy="subject", cascade={CascadeType.ALL})
    private List<Course> courses;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
