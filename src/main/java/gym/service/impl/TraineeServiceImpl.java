package gym.service.impl;

import gym.dao.TraineeDao;
import gym.domain.Trainee;
import gym.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger log = LoggerFactory.getLogger(TraineeServiceImpl.class);
    private TraineeDao traineeDao;

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        log.info("Creating trainee: {} {}", trainee.getFirstName(), trainee.getLastName());
        Trainee saved = traineeDao.saveTrainee(trainee);
        log.info("Created trainee with id={}", saved.getUserId());
        return saved;
    }

    @Override
    public Trainee getTrainee(Long id) {
        log.info("Fetching trainee with id={}", id);
        return traineeDao.getTraineeById(id);
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        log.info("Updating trainee with id={}", trainee.getUserId());
        return traineeDao.updateTrainee(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        log.info("Deleting trainee with id={}", id);
        traineeDao.deleteTraineeById(id);
    }
}