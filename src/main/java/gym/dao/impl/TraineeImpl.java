package gym.dao.impl;

import gym.dao.TraineeDao;
import gym.domain.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TraineeImpl implements TraineeDao {

    private static final Logger log = LoggerFactory.getLogger(TraineeImpl.class);

    private Map<Long, Trainee> traineeStorage;

    @Autowired
    public void setTraineeStorage(Map<Long, Trainee> traineeStorage) {
        this.traineeStorage = traineeStorage;
    }

    @Override
    public Trainee saveTrainee(Trainee trainee) {
        log.debug("Saving trainee with id={}", trainee.getUserId());
        traineeStorage.put(trainee.getUserId(), trainee);
        log.info("Trainee saved: id={}, username={}", trainee.getUserId(), trainee.getUsername());
        return trainee;
    }

    @Override
    public Trainee getTraineeById(Long id) {
        log.debug("Looking up trainee with id={}", id);
        Trainee trainee = traineeStorage.get(id);
        if (trainee == null) {
            log.warn("Trainee not found: id={}", id);
        }
        return trainee;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        log.debug("Updating trainee with id={}", trainee.getUserId());
        if (!traineeStorage.containsKey(trainee.getUserId())) {
            log.error("Cannot update — trainee not found: id={}", trainee.getUserId());
            throw new IllegalArgumentException("Trainee not found: id=" + trainee.getUserId());
        }
        traineeStorage.put(trainee.getUserId(), trainee);
        log.info("Trainee updated: id={}", trainee.getUserId());
        return trainee;
    }

    @Override
    public void deleteTraineeById(Long id) {
        log.debug("Deleting trainee with id={}", id);
        boolean existed = traineeStorage.remove(id) != null;
        if (existed) {
            log.info("Trainee deleted: id={}", id);
        } else {
            log.warn("Delete requested for non-existent trainee: id={}", id);
        }
    }

    @Override
    public List<Trainee> findAll() {
        log.debug("Fetching all trainees, count={}", traineeStorage.size());
        return List.copyOf(traineeStorage.values());
    }
}