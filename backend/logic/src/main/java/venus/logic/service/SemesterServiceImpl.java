package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Semester;
import venus.dal.model.User;
import venus.dal.repository.SemesterRepository;
import venus.logic.dto.SemesterDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Iterable<Semester> findAll() {
        return semesterRepository.findAll();
    }

    /*@Override
    public Iterable<Semester> findAllForUser(User user) {

        return null;
    }*/

    @Override
    public Optional<Semester> current() {
        Date now = Date.valueOf(LocalDate.now());
        return semesterRepository.findOneByFromBeforeAndToAfter(now, now);
    }

    @Override
    public List<SemesterDTO> convertToDTOs(List<Semester> semesters, User user) {
        return semesters
                .stream()
                .map(n -> SemesterDTO.create(n, user))
                .collect(Collectors.toList());
    }
}
