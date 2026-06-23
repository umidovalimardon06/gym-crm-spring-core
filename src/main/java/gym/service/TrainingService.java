package gym.service;

import gym.domain.Training;

import java.util.List;

public interface TrainingService {
    Training createTraining(Training training);
    Training getTrainingById(Long id);
    List<Training> getAllTraining();
}
