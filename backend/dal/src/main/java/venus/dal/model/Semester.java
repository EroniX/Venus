package venus.dal.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "semesters")
public class Semester extends BaseEntity {

    @Column(nullable = false)
    private Date from;

    @Column(nullable = false)
    private Date to;

    @OneToMany(mappedBy="semester", cascade={CascadeType.ALL})
    private List<Course> courses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_semesters",
            joinColumns = @JoinColumn(name = "semesterid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"))
    private List<User> students;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public Boolean current() {
        Date now = Date.valueOf(LocalDate.now());
        return now.after(from) && now.before(to);
    }
}
