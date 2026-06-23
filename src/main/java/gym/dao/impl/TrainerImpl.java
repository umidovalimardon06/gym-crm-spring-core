package gym.dao.impl;

import gym.dao.TrainerDao;
import gym.domain.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TrainerImpl implements TrainerDao {

    private static final Logger log = LoggerFactory.getLogger(TrainerImpl.class);

    private Map<Long, Trainer> trainerStorage;

    @Autowired
    public void setTrainerStorage(Map<Long, Trainer> trainerStorage) {
        this.trainerStorage = trainerStorage;
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        log.debug("Saving trainer with id={}", trainer.getUserId());
        trainerStorage.put(trainer.getUserId(), trainer);
        log.info("Trainer saved: id={}, username={}", trainer.getUserId(), trainer.getUsername());
        return trainer;
    }

    @Override
    public Trainer getTrainerById(Long id) {
        log.debug("Looking up trainer with id={}", id);
        Trainer trainer = trainerStorage.get(id);
        if (trainer == null) {
            log.warn("Trainer not found: id={}", id);
        }
        return trainer;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        log.debug("Updating trainer with id={}", trainer.getUserId());
        if (!trainerStorage.containsKey(trainer.getUserId())) {
            log.error("Cannot update — trainer not found: id={}", trainer.getUserId());
            throw new IllegalArgumentException("Trainer not found: id=" + trainer.getUserId());
        }
        trainerStorage.put(trainer.getUserId(), trainer);
        log.info("Trainer updated: id={}", trainer.getUserId());
        return trainer;
    }

    @Override
    public List<Trainer> findAll() {
        log.debug("Fetching all trainers, count={}", trainerStorage.size());
        return List.copyOf(trainerStorage.values());
    }
}