package venus.logic.dto;

import venus.dal.model.Semester;
import venus.dal.model.User;

import java.util.Date;

public class SemesterDTO {
    private Date from;
    private Date to;
    private Boolean current;
    private Boolean registered;

    public SemesterDTO() {
    }

    public SemesterDTO(Date from, Date to, Boolean current, Boolean registered) {
        this.from = from;
        this.to = to;
        this.current = current;
        this.registered = registered;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public static SemesterDTO create(Semester semester, User user) {
        return new SemesterDTO(
            semester.getFrom(),
            semester.getTo(),
            semester.current(),
            user.hasSemester(semester.getId()));
    }
}
