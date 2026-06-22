package gym.dao.impl;

import gym.dao.TrainerDao;
import gym.domain.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TrainerImpl implements TrainerDao {
    private Map<Long, Trainer> trainerStorage;

    @Autowired
    public void setTrainerStorage(Map<Long, Trainer> trainerStorage) {
        this.trainerStorage = trainerStorage;
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        trainerStorage.put(trainer.getUserId(), trainer);
        return trainer;
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerStorage.get(id);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        if (trainerStorage.containsKey(trainer.getUserId())) {
            trainerStorage.put(trainer.getUserId(), trainer);
            return trainer;
        }
        return trainerStorage.get(trainer.getUserId());
    }

    @Override
    public List<Trainer> findAll() {
        return List.copyOf(trainerStorage.values());
    }
}