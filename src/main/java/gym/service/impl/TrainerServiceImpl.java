package gym.service.impl;

import gym.dao.TrainerDao;
import gym.domain.Trainer;
import gym.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    private static final Logger log = LoggerFactory.getLogger(TrainerServiceImpl.class);
    private TrainerDao trainerDao;

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        log.info("Creating trainer: {} {}", trainer.getFirstName(), trainer.getLastName());
        Trainer saved = trainerDao.saveTrainer(trainer);
        log.info("Created trainer with id={}", saved.getUserId());
        return saved;
    }

    @Override
    public Trainer getTrainerById(Long id) {
        log.info("Fetching trainer with id={}", id);
        return trainerDao.getTrainerById(id);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        log.info("Updating trainer with id={}", trainer.getUserId());
        return trainerDao.updateTrainer(trainer);
    }
}