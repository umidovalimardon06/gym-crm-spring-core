package gym.service.impl;

import gym.dao.TrainingDao;
import gym.domain.Training;
import gym.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {

    private static final Logger log = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private TrainingDao trainingDao;

    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Override
    public Training createTraining(Training training) {
        log.info("Creating training: {}", training.getTrainingName());
        Training saved = trainingDao.saveTraining(training);
        log.info("Created training with id={}", saved.getId());
        return saved;
    }

    @Override
    public Training getTrainingById(Long id) {
        log.info("Fetching training with id={}", id);
        return trainingDao.getTrainingById(id);
    }
}