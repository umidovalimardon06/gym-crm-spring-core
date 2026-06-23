package service;

import gym.dao.TraineeDao;
import gym.dao.TrainerDao;
import gym.domain.Trainee;
import gym.service.impl.TraineeServiceImpl;
import gym.utility.PasswordGenerator;
import gym.utility.UsernameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TraineeServiceImplTest {
    private TraineeServiceImpl service;
    private TraineeDao traineeDao;
    private TrainerDao trainerDao;
    private UsernameGenerator usernameGenerator;
    private PasswordGenerator passwordGenerator;

    @BeforeEach
    void setUp() {
        traineeDao = mock(TraineeDao.class);
        trainerDao = mock(TrainerDao.class);
        usernameGenerator = mock(UsernameGenerator.class);
        passwordGenerator = mock(PasswordGenerator.class);
        service = new TraineeServiceImpl();
        service.setTraineeDao(traineeDao);
        service.setTrainerDao(trainerDao);
        service.setUsernameGenerator(usernameGenerator);
        service.setPasswordGenerator(passwordGenerator);
    }

    @Test
    void createTrainee_success() {
        Trainee trainee = buildTrainee(1L, "John", "Smith");

        when(usernameGenerator.generateUsername(
                eq("John"),
                eq("Smith"),
                anySet()))
                .thenReturn("john.smith");
        when(traineeDao.saveTrainee(any()))
                .thenReturn(trainee);

        Trainee result = service.createTrainee(trainee);
        assertEquals("john.smith", result.getUsername());
        verify(traineeDao).saveTrainee(trainee);
    }

    @Test
    void getTraineeById_returnsTrainee() {
        Trainee trainee = buildTrainee(10L, "Anna", "White");

        when(traineeDao.getTraineeById(10L))
                .thenReturn(trainee);
        Trainee result = service.getTraineeById(10L);
        assertNotNull(result);
        assertEquals("Anna", result.getFirstName());
        verify(traineeDao).getTraineeById(10L);
    }

    @Test
    @DisplayName("update should update trainee and return updated object")
    void updateTrainee_success() {
        Trainee trainee = buildTrainee(20L, "Mike", "Brown");
        Trainee updated = service.updateTrainee(trainee);
        assertEquals(20L, updated.getUserId());
        verify(traineeDao).updateTrainee(trainee);
    }

    @Test
    void deleteTrainee_success() {
        service.deleteTrainee(5L);
        verify(traineeDao).deleteTraineeById(5L);
    }

    private Trainee buildTrainee(Long id, String first, String last) {
        Trainee t = new Trainee();
        t.setUserId(id);
        t.setFirstName(first);
        t.setLastName(last);
        return t;
    }
}