package venus.logic.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import venus.dal.model.Semester;
import venus.dal.repository.SemesterRepository;

import java.util.Date;

@Service
@SessionScope
@Data
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public Semester current() {
    	return null;
    }
}
