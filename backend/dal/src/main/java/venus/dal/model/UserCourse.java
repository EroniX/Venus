package venus.dal.model;

import javax.persistence.*;

@Entity
@Table(name = "users_courses")
public class UserCourse extends BaseEntity {
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

    public static UserCourse make(User student, Course course) {
        UserCourse userCourse = new UserCourse();
        userCourse.setStudent(student);
        userCourse.setCourse(course);
        return userCourse;
    }
}
