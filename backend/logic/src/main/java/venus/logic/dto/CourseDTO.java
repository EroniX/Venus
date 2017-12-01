package venus.logic.dto;

import venus.dal.model.Course;
import venus.dal.model.User;
import venus.dal.model.UserCourse;

import java.util.Optional;

public class CourseDTO {
    private int id;
    private String teacherName;
    private int subjectId;
    private String subjectName;
    private String subjectCode;
    private int capacity;
    private Integer mark;
    private Boolean registered;

    public CourseDTO() {
    }

    public CourseDTO(
            int id,
            String teacherName,
            int subjectId,
            String subjectName,
            String subjectCode,
            int capacity,
            Integer mark,
            Boolean registered) {

        this.setId(id);
        this.setTeacherName(teacherName);
        this.setSubjectId(subjectId);
        this.setSubjectName(subjectName);
        this.setSubjectCode(subjectCode);
        this.setCapacity(capacity);
        this.setMark(mark);
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public static CourseDTO create(Course course, User user) {
        Optional<UserCourse> userCourse = user.getUserCourseForCourse(course.getId());
        return new CourseDTO(
            course.getId(),
            course.getTeacher().getEmail(),
            course.getSubject().getId(),
            course.getSubject().getName(),
            course.getSubject().getCode(),
            course.getCapacity(),
            userCourse.isPresent()
                ? userCourse.get().getMark()
                : null,
            userCourse.isPresent());
    }
}
