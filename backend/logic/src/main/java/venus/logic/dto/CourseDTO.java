package venus.logic.dto;

import venus.dal.model.Course;
import venus.dal.model.User;

public class CourseDTO {
    private int id;
    private String teacherName;
    private String subjectName;
    private String subjectCode;
    private int limit;
    private Boolean registered;

    public CourseDTO() {
    }

    public CourseDTO(
            int id,
            String teacherName,
            String subjectName,
            String subjectCode,
            int limit,
            Boolean registered) {

        this.setId(id);
        this.setTeacherName(teacherName);
        this.setSubjectName(subjectName);
        this.setSubjectCode(subjectCode);
        this.setLimit(limit);
        this.setRegistered(registered);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public static CourseDTO create(Course course, User user) {
        return new CourseDTO(
                course.getId(),
                course.getTeacher().getEmail(),
                course.getSubject().getName(),
                course.getSubject().getCode(),
                course.getLimit(),
                user.hasCourse(course.getId()));
    }
}
