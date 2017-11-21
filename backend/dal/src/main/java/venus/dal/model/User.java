package venus.dal.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getTeachedCourses() {
        return teachedCourses;
    }

    public void setTeachedCourses(List<Course> teachedCourses) {
        this.teachedCourses = teachedCourses;
    }

    public List<UserCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<UserCourse> courses) {
        this.courses = courses;
    }

    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public Boolean hasRoles() {
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

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<String> getRoleNames() {
        return getRoles()
                .stream()
                .map(n -> n.getName())
                .collect(Collectors.toList());
    }

    public Boolean hasTraining(int id) {
        return getTrainings()
                .stream()
                .anyMatch(n -> n.getId() == id);
    }

    public void removeTraining(int id) {
        getTrainings()
                .removeIf(n -> n.getId() == id);
    }

    public void addTraining(Training training) {
        if(!hasTraining(training.getId())) {
            getTrainings()
                    .add(training);
        }
    }

    public Boolean hasTeachedCourse(int id) {
        return getTeachedCourses()
                .stream()
                .anyMatch(n -> n.getId() == id);
    }

    public void removeUserCourse(int courseId) {
        getCourses()
                .removeIf(n -> n.getCourse().getId() == courseId);
    }

    public void addUserCourse(UserCourse userCourse) {
        if(!hasCourse(userCourse.getCourse().getId())) {
            getCourses()
                    .add(userCourse);
        }
    }

    public Boolean hasCourse(int courseId) {
        return getCourses()
                .stream()
                .anyMatch(n -> n.getCourse()
                        .getId() == courseId);
    }
}
