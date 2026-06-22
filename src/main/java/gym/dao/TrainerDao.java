package gym.dao;

import gym.domain.Trainer;

import java.util.List;

public interface TrainerDao {
    Trainer saveTrainer(Trainer trainer);
    Trainer getTrainerById(Long id);
    Trainer updateTrainer(Trainer trainer);
    List<Trainer> findAll();
}