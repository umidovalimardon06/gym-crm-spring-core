package gym.dao;

import gym.domain.Trainee;

import java.util.List;

public interface TraineeDao {
    Trainee saveTrainee(Trainee trainee);
    Trainee getTraineeById(Long id);
    Trainee updateTrainee(Trainee trainee);
    void deleteTraineeById(Long id);
    List<Trainee> findAll();
}