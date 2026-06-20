package gym.dao;

import gym.domain.Trainee;

public interface TraineeDao {
    Trainee saveTrainee(Trainee trainee);
    Trainee getTraineeById(Long id);
    Trainee updateTrainee(Trainee trainee);
    void deleteTraineeById(Long id);
}