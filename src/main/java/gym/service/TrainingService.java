package gym.service;

import gym.domain.Training;

public interface TrainingService {
    Training saveTraining(Training training);
    Training getTrainingById(Long id);
}
