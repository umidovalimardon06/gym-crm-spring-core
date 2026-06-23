import gym.dao.impl.TrainingImpl;
import gym.domain.Training;
import gym.domain.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TrainingImplTest {

    private TrainingImpl trainingDao;
    private Map<Long, Training> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        trainingDao = new TrainingImpl();
        trainingDao.setTrainingStorage(storage);
    }

    @Test
    void saveAndGetById_storesAndReturnsTraining() {
        Training training = buildTraining(100L, "Cardio Session");
        Training saved = trainingDao.saveTraining(training);
        Training fetched = trainingDao.getTrainingById(100L);
        assertEquals(training, saved);
        assertNotNull(fetched);
        assertEquals("Cardio Session", fetched.getTrainingName());
    }

    @Test
    void getTrainingById_notFound_returnsNull() {
        assertNull(trainingDao.getTrainingById(999L));
    }

    private Training buildTraining(Long id, String name) {
        Training t = new Training();
        t.setId(id);
        t.setTraineeId(1L);
        t.setTrainerId(10L);
        t.setTrainingName(name);
        t.setTrainingType(TrainingType.CARDIO);
        t.setTrainingDate(LocalDate.now());
        t.setTrainingDuration(Duration.ofSeconds(60));
        return t;
    }
}