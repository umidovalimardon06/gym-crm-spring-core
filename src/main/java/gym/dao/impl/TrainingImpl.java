package gym.dao.impl;

import gym.dao.TrainingDao;
import gym.domain.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TrainingImpl implements TrainingDao {

    private static final Logger log = LoggerFactory.getLogger(TrainingImpl.class);

    private Map<Long, Training> trainingStorage;

    @Autowired
    public void setTrainingStorage(Map<Long, Training> trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Override
    public Training saveTraining(Training training) {
        log.debug("Saving training with id={}", training.getId());
        trainingStorage.put(training.getId(), training);
        log.info("Training saved: id={}, name={}", training.getId(), training.getTrainingName());
        return training;
    }

    @Override
    public Training getTrainingById(Long id) {
        log.debug("Looking up training with id={}", id);
        Training training = trainingStorage.get(id);
        if (training == null) {
            log.warn("Training not found: id={}", id);
        }
        return training;
    }

    @Override
    public List<Training> getAll() {
        log.debug("Fetching all trainings, count={}", trainingStorage.size());
        return List.copyOf(trainingStorage.values());
    }
}