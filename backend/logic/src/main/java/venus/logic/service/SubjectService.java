package venus.logic.service;

import venus.dal.model.Subject;
import venus.dal.model.User;
import venus.logic.dto.SubjectDTO;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    void save(Subject subject);
    void delete(int id);
    Iterable<Subject> findAll();
    Optional<Subject> findById(int id);
    List<SubjectDTO> convertToDTOs(List<Subject> subjects, User user);
}
