package gym.facade;

import gym.domain.Trainee;
import gym.domain.Trainer;
import gym.domain.Training;
import gym.service.TraineeService;
import gym.service.TrainerService;
import gym.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GymFacade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public GymFacade(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }


    public Trainee createTrainee(Trainee trainee) {
        return traineeService.createTrainee(trainee);
    }

    public Trainee getTraineeById(Long id) {
        return traineeService.getTraineeById(id);
    }

    public Trainee updateTrainee(Trainee trainee) {
        return traineeService.updateTrainee(trainee);
    }

    public void deleteTrainee(Long id) {
        traineeService.deleteTrainee(id);
    }



    public Trainer createTrainer(Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    public Trainer getTrainerById(Long id) {
        return trainerService.getTrainerById(id);
    }

    public Trainer updateTrainer(Trainer trainer) {
        return trainerService.updateTrainer(trainer);
    }



    public Training createTraining(Training training) {
        return trainingService.createTraining(training);
    }

    public Training getTrainingById(Long id) {
        return trainingService.getTrainingById(id);
    }

    public List<Training> getAllTraining() {
        return trainingService.getAllTraining();
    }
}