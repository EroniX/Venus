package venus.logic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.ALL})
    private List<Course> teachedCourses;

    @ManyToMany(mappedBy = "students")
    private List<Training> trainings;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL})
    private List<UserCourse> courses;

    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public Boolean HasRoles() {
        return roles != null && !roles.isEmpty();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleNames() {
        if (roles != null) {
            return roles
                    .stream()
                    .map(n -> n.getName())
                    .collect(Collectors.toList());
        }
        return null;
    }
}
