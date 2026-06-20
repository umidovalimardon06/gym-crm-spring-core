package gym.dao;

import gym.domain.Trainer;

public interface TrainerDao {
    Trainer saveTrainer(Trainer trainer);
    Trainer getTrainerById(Long id);
    Trainer updateTrainer(Trainer trainer);
}