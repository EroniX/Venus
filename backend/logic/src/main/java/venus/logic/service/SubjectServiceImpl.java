package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Subject;
import venus.dal.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    public void delete(int id) {
        subjectRepository.delete(id);
    }

    public Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(int id) {
        return subjectRepository.findOne(id);
    }
}
