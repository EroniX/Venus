package venus.logic.service;

import venus.dal.model.Training;
import venus.dal.model.User;
import venus.logic.dto.TrainingDTO;

import java.util.List;
import java.util.Optional;

public interface TrainingService {
    void save(Training training);
    void delete(int id);
    Iterable<Training> findAll();
    Iterable<Training> findAllUnregistered(User user);
    Optional<Training> findById(int id);
    Iterable<TrainingDTO> convertToDTOs(List<Training> trainings);
}
