package gym.service.impl;

import gym.dao.TrainingDao;
import gym.domain.Training;
import gym.service.TrainingService;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingDao trainingDao;

    public TrainingServiceImpl(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Override
    public Training saveTraining(Training training) {
        return trainingDao.saveTraining(training);
    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingDao.getTrainingById(id);
    }
}