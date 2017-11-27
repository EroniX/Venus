package venus.logic.service;

import venus.dal.model.Semester;
import venus.dal.model.User;
import venus.logic.dto.SemesterDTO;

import java.util.List;
import java.util.Optional;

public interface SemesterService {
    Iterable<Semester> findAll();
    Optional<Semester> current();
    List<SemesterDTO> convertToDTOs(List<Semester> semesters, User user);
}
