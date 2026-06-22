package gym.service;

import gym.domain.Training;

public interface TrainingService {
    Training createTraining(Training training);
    Training getTrainingById(Long id);
}
