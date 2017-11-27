package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Subject;
import venus.dal.model.User;
import venus.dal.repository.SubjectRepository;
import venus.logic.dto.SubjectDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void delete(int id) {
        subjectRepository.delete(id);
    }

    @Override
    public Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findById(int id) {
        return Optional.of(subjectRepository.findOne(id));
    }

    @Override
    public List<SubjectDTO> convertToDTOs(List<Subject> subjects, User user) {
        return subjects
                .stream()
                .map(n -> SubjectDTO.create(n, user))
                .collect(Collectors.toList());
    }
}
