package venus.logic.dto;

import venus.dal.model.Subject;
import venus.dal.model.User;

public class SubjectDTO {
    private int id;
    private String name;
    private String code;
    private Boolean registered;

    public SubjectDTO() {
    }

    public SubjectDTO(int id, String name, String code, Boolean registered) {
        this.setId(id);
        this.setName(name);
        this.setCode(code);
        this.setRegistered(registered);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public static SubjectDTO create(Subject subject, User user) {
        return new SubjectDTO(
            subject.getId(),
            subject.getName(),
            subject.getCode(),
            user.hasSubject(subject.getId()));
    }
}
