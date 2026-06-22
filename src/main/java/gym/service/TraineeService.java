package gym.service;

import gym.domain.Trainee;

public interface TraineeService {
    Trainee createTrainee(Trainee trainee);
    Trainee getTraineeById(Long id);
    Trainee updateTrainee(Trainee trainee);
    void deleteTrainee(Long id);
}