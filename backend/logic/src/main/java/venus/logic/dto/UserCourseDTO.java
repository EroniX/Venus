package venus.logic.dto;

import venus.dal.model.UserCourse;

public class UserCourseDTO {
    private int userId;
    private String username;
    private String email;
    private Integer mark;

    public UserCourseDTO() {
    }

    public UserCourseDTO(
            int userId,
            String username,
            String email,
            Integer mark) {

        this.setUserId(userId);
        this.setUsername(username);
        this.setEmail(email);
        this.setMark(mark);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
            userCourse.getStudent().getId(),
            userCourse.getStudent().getUsername(),
            userCourse.getStudent().getEmail(),
            userCourse.getMark()
        );
    }
}
