package venus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="teacher", cascade={CascadeType.ALL})
    private List<Course> teachedCourses;

    @ManyToMany(mappedBy = "students")
    private List<Training> trainings;

    @OneToMany(mappedBy="student", cascade={CascadeType.ALL})
    private List<UserCourse> courses;

    public enum Role {
        GUEST, USER, ADMIN
    }
}
