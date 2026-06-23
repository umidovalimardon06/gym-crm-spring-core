import gym.dao.impl.TrainerImpl;
import gym.domain.Trainer;
import gym.domain.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TrainerServiceImplTest {

    private TrainerImpl trainerDao;
    private Map<Long, Trainer> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        trainerDao = new TrainerImpl();
        trainerDao.setTrainerStorage(storage);
    }

    @Test
    void saveAndGetById_storesAndReturnsTrainer() {
        Trainer trainer = buildTrainer(10L, "Bobur", "Toshmatov");

        Trainer saved = trainerDao.saveTrainer(trainer);
        Trainer fetched = trainerDao.getTrainerById(10L);

        assertEquals(trainer, saved);
        assertEquals("Bobur", fetched.getFirstName());
    }

    @Test
    void getTrainerById_notFound_returnsNull() {
        assertNull(trainerDao.getTrainerById(999L));
    }

    @Test
    void updateTrainer_existing_replacesValue() {
        Trainer original = buildTrainer(10L, "Bobur", "Toshmatov");
        trainerDao.saveTrainer(original);

        Trainer updated = buildTrainer(10L, "Bobur Updated", "Toshmatov");
        trainerDao.updateTrainer(updated);

        assertEquals("Bobur Updated", trainerDao.getTrainerById(10L).getFirstName());
    }

    @Test
    void findAll_returnsAllTrainers() {
        trainerDao.saveTrainer(buildTrainer(10L, "Bobur", "Toshmatov"));
        trainerDao.saveTrainer(buildTrainer(11L, "Nodira", "Rahimova"));

        List<Trainer> all = trainerDao.findAll();

        assertEquals(2, all.size());
    }

    private Trainer buildTrainer(Long id, String firstName, String lastName) {
        Trainer t = new Trainer();
        t.setUserId(id);
        t.setFirstName(firstName);
        t.setLastName(lastName);
        t.setSpecialization(TrainingType.CARDIO);
        return t;
    }
}