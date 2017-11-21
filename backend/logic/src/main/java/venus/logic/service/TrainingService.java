package venus.logic.service;

import venus.dal.model.Training;

public interface TrainingService {
    void save(Training training);
    void delete(int id);
    Iterable<Training> findAll();
    Training findById(int id);
}
