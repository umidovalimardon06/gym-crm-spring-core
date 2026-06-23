import gym.domain.Trainee;
import gym.domain.Trainer;
import gym.domain.Training;
import gym.facade.GymFacade;
import gym.service.TraineeService;
import gym.service.TrainerService;
import gym.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymFacadeTest {

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private GymFacade facade;

    @Test
    void createTrainee_delegatesToService() {
        Trainee trainee = new Trainee();
        trainee.setUserId(1L);
        when(traineeService.createTrainee(trainee)).thenReturn(trainee);

        Trainee result = facade.createTrainee(trainee);

        assertEquals(trainee, result);
        verify(traineeService).createTrainee(trainee);
    }

    @Test
    void getTraineeById_delegatesToService() {
        Trainee trainee = new Trainee();
        trainee.setUserId(1L);
        when(traineeService.getTraineeById(1L)).thenReturn(trainee);

        Trainee result = facade.getTraineeById(1L);

        assertEquals(trainee, result);
        verify(traineeService).getTraineeById(1L);
    }

    @Test
    void deleteTrainee_delegatesToService() {
        facade.deleteTrainee(1L);

        verify(traineeService).deleteTrainee(1L);
    }

    @Test
    void createTrainer_delegatesToService() {
        Trainer trainer = new Trainer();
        trainer.setUserId(10L);
        when(trainerService.createTrainer(trainer)).thenReturn(trainer);

        Trainer result = facade.createTrainer(trainer);

        assertEquals(trainer, result);
        verify(trainerService).createTrainer(trainer);
    }

    @Test
    void createTraining_delegatesToService() {
        Training training = new Training();
        training.setId(100L);
        when(trainingService.createTraining(training)).thenReturn(training);

        Training result = facade.createTraining(training);

        assertEquals(training, result);
        verify(trainingService).createTraining(training);
    }
}