package venus.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCourse extends BaseEntity {

    public UserCourse(User student, Course course) {
        this(student, course, null);
    }

    public UserCourse(User student, Course course, Integer mark) {
        this.setStudent(student);
        this.setCourse(course);
        this.setMark(mark);
    }

    @ManyToOne
    @JoinColumn(name = "userid")
    private User student;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;

    @Column
    private Integer mark;

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
