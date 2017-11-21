package venus.dal.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "teacherid")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "semesterid")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "subjectid")
    private Subject subject;

    @OneToMany(mappedBy="course", cascade={CascadeType.ALL})
    private List<UserCourse> studentCourses;

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<UserCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<UserCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
