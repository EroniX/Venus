package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Training;
import venus.dal.model.User;
import venus.dal.repository.TrainingRepository;
import venus.logic.dto.TrainingDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public void save(Training training) {
        trainingRepository.save(training);
    }

    @Override
    public void delete(int id) {
        trainingRepository.delete(id);
    }

    @Override
    public Iterable<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Iterable<Training> findAllUnregistered(User user) {
        return trainingRepository.findByStudentsIsNotContaining(user);
    }

    @Override
    public Optional<Training> findById(int id) {
        return Optional.of(trainingRepository.findOne(id));
    }

    @Override
    public List<TrainingDTO> convertToDTOs(List<Training> trainings) {
        return trainings
                .stream()
                .map(n -> new TrainingDTO(n.getId(), n.getName()))
                .collect(Collectors.toList());
    }
}
