package venus.logic.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import venus.dal.model.Training;
import venus.dal.repository.TrainingRepository;
import venus.security.service.SecurityService;

import java.util.List;

@Service
@SessionScope
@Data
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public void save(Training training) {
        trainingRepository.save(training);
    }

    public void delete(int id) {
        trainingRepository.delete(id);
    }

    public Iterable<Training> findAll() {
        return trainingRepository.findAll();
    }

    public Training findById(int id) {
        return trainingRepository.findOne(id);
    }
}
