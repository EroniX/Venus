package venus.logic.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import venus.logic.model.Semester;
import venus.repository.SemesterRepository;

import java.util.Date;

@Service
@SessionScope
@Data
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public Semester current() {
        Date date = new Date();
        for(Semester semester : semesterRepository.findAll()) {
            if(semester.getFrom().before(date) && semester.getTo().after(date)) {
                return semester;
            }
        }
        return null;
    }
}
