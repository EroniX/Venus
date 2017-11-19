package venus.dal.model;

import javax.persistence.*;
import java.sql.Date;
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
}
