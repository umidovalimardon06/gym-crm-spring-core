package gym.dao.impl;

import gym.dao.TraineeDao;
import gym.domain.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TraineeImpl implements TraineeDao {
    private Map<Long, Trainee> traineeStorage;

    @Autowired
    public void setTraineeStorage(Map<Long, Trainee> traineeStorage) {
        this.traineeStorage = traineeStorage;
    }

    @Override
    public Trainee saveTrainee(Trainee trainee) {
        return traineeStorage.put(trainee.getUserId(), trainee);
    }

    @Override
    public Trainee getTraineeById(Long id) {
        return traineeStorage.get(id);
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        if (traineeStorage.containsKey(trainee.getUserId())) {
            traineeStorage.put(trainee.getUserId(), trainee);
            return trainee;
        }
        return traineeStorage.get(trainee.getUserId());
    }

    @Override
    public void deleteTraineeById(Long id) {
        traineeStorage.remove(id);
    }
}