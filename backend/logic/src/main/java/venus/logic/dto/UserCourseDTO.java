package venus.logic.dto;

import venus.dal.model.UserCourse;

public class UserCourseDTO {
    private String subjectName;
    private int courseId;
    private String username;
    private String email;
    private Integer mark;

    public UserCourseDTO() {
    }

    public UserCourseDTO(
            String subjectName,
            int courseId,
            String username,
            String email,
            Integer mark) {

        this.setSubjectName(subjectName);
        this.setCourseId(courseId);
        this.setUsername(username);
        this.setEmail(email);
        this.setMark(mark);
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public static UserCourseDTO create(UserCourse userCourse) {
        return new UserCourseDTO(
            userCourse.getCourse().getSubject().getName(),
            userCourse.getCourse().getId(),
            userCourse.getStudent().getUsername(),
            userCourse.getStudent().getEmail(),
            userCourse.getMark()
        );
    }
}
