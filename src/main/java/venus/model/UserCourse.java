package venus.model;

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

    @ManyToOne
    @JoinColumn(name = "userid")
    private User student;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;

    @Column
    private int mark;
}
