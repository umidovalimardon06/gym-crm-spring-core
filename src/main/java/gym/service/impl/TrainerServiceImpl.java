package gym.service.impl;

import gym.dao.TrainerDao;
import gym.domain.Trainer;
import gym.service.TrainerService;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerDao trainerDao;

    public TrainerServiceImpl(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        return trainerDao.saveTrainer(trainer);
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerDao.getTrainerById(id);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        return trainerDao.updateTrainer(trainer);
    }
}