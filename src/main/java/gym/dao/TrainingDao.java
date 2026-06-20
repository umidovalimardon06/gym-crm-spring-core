package gym.dao;

import gym.domain.Training;

public interface TrainingDao {
    Training saveTraining(Training training);
    Training getTrainingById(Long id);
}