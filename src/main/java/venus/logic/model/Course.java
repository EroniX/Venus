package venus.logic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}
