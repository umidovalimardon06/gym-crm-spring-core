package gym.service.impl;

import gym.dao.TraineeDao;
import gym.domain.Trainee;
import gym.service.TraineeService;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {
    private TraineeDao traineeDao;

    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        return traineeDao.saveTrainee(trainee);
    }

    @Override
    public Trainee getTrainee(Long id) {
        return traineeDao.getTraineeById(id);
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        return traineeDao.updateTrainee(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeDao.deleteTraineeById(id);
    }
}