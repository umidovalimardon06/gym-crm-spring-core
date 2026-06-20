package gym.dao.impl;

import gym.dao.TrainingDao;
import gym.domain.Trainee;
import gym.domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainingImpl implements TrainingDao {
    private Map<Long, Training> trainingStorage;

    @Autowired
    public void setTrainingStorage(Map<Long, Training> trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Override
    public Training saveTraining(Training training) {
        return trainingStorage.put(training.getId(), training);
    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingStorage.get(id);
    }
}