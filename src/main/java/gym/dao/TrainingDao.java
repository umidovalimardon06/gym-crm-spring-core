package gym.dao;

import gym.domain.Training;

import java.util.List;

public interface TrainingDao {
    Training saveTraining(Training training);
    Training getTrainingById(Long id);
    List<Training> getAll();
}