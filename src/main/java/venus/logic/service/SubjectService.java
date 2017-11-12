package venus.logic.service;

import venus.dal.model.Subject;

public interface SubjectService {
    void save(Subject subject);
    void delete(int id);
    Iterable<Subject> findAll();
    Subject findById(int id);
}
