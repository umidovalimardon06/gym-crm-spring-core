package dao;

import gym.dao.impl.TraineeImpl;
import gym.domain.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TraineeDaoImplTest {

    private TraineeImpl traineeDao;
    private Map<Long, Trainee> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        traineeDao = new TraineeImpl();
        traineeDao.setTraineeStorage(storage);
    }

    @Test
    void saveAndGetById_storesAndReturnsTrainee() {
        Trainee trainee = buildTrainee(1L, "John", "Doe");

        Trainee saved = traineeDao.saveTrainee(trainee);
        Trainee fetched = traineeDao.getTraineeById(1L);

        assertEquals(trainee, saved);
        assertEquals("John", fetched.getFirstName());
        assertEquals(1, storage.size());
    }

    @Test
    void getTraineeById_notFound_returnsNull() {
        Trainee result = traineeDao.getTraineeById(999L);

        assertNull(result);
    }

    @Test
    void updateTrainee_existing_replacesValue() {
        Trainee original = buildTrainee(1L, "John", "Doe");
        traineeDao.saveTrainee(original);

        Trainee updated = buildTrainee(1L, "John Updated", "Doe Updated");
        traineeDao.updateTrainee(updated);

        Trainee fetched = traineeDao.getTraineeById(1L);
        assertEquals("John Updated", fetched.getFirstName());
        assertEquals("Doe Updated", fetched.getLastName());
    }

    @Test
    void deleteTraineeById_removesFromStorage() {
        Trainee trainee = buildTrainee(1L, "John", "Doe");
        traineeDao.saveTrainee(trainee);

        traineeDao.deleteTraineeById(1L);

        assertNull(traineeDao.getTraineeById(1L));
        assertTrue(storage.isEmpty());
    }

    @Test
    void findAll_returnsAllTrainees() {
        traineeDao.saveTrainee(buildTrainee(1L, "John", "Doe"));
        traineeDao.saveTrainee(buildTrainee(2L, "Jane", "Smith"));

        List<Trainee> all = traineeDao.findAll();

        assertEquals(2, all.size());
    }

    private Trainee buildTrainee(Long id, String firstName, String lastName) {
        Trainee t = new Trainee();
        t.setUserId(id);
        t.setFirstName(firstName);
        t.setLastName(lastName);
        return t;
    }
}