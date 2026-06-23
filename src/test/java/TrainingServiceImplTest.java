import gym.dao.TrainingDao;
import gym.domain.Training;
import gym.service.impl.TrainingServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest {
    private TrainingServiceImpl service;
    private TrainingDao trainingDao;

    @BeforeEach
    void setUp() {
        trainingDao = mock(TrainingDao.class);
        service = new TrainingServiceImpl();
        service.setTrainingDao(trainingDao);
    }

    @Test
    void createTraining_shouldSaveAndReturnTraining() {
        Training training = buildTraining(1L, "Cardio");

        when(trainingDao.saveTraining(training))
                .thenReturn(training);

        Training result = service.createTraining(training);
        assertEquals(training, result);
        verify(trainingDao).saveTraining(training);
    }

    @Test
    void getTrainingById_shouldReturnTraining() {
        Training training = buildTraining(10L, "Strength");

        when(trainingDao.getTrainingById(10L))
                .thenReturn(training);

        Training result = service.getTrainingById(10L);
        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(trainingDao).getTrainingById(10L);
    }

    private Training buildTraining(Long id, String name) {
        Training training = new Training();
        training.setId(id);
        training.setTrainingName(name);
        return training;
    }
}