package venus.logic.service;

import venus.dal.model.Semester;
import venus.logic.exceptions.CurrentSemesterNotFound;

public interface SemesterService {
    Semester current() throws CurrentSemesterNotFound;
}
