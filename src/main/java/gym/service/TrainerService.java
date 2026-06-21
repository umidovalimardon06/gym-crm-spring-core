package gym.service;

import gym.domain.Trainer;

public interface TrainerService {
    Trainer saveTrainer(Trainer trainer);
    Trainer getTrainerById(Long id);
    Trainer updateTrainer(Trainer trainer);
}
