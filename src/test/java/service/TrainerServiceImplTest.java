package service;

import gym.dao.TraineeDao;
import gym.dao.TrainerDao;
import gym.domain.Trainee;
import gym.domain.Trainer;
import gym.domain.TrainingType;
import gym.service.impl.TrainerServiceImpl;
import gym.utility.PasswordGenerator;
import gym.utility.UsernameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    private TrainerServiceImpl service;
    private TrainerDao trainerDao;
    private TraineeDao traineeDao;
    private UsernameGenerator usernameGenerator;
    private PasswordGenerator passwordGenerator;

    @BeforeEach
    void setUp() {
        trainerDao        = mock(TrainerDao.class);
        traineeDao        = mock(TraineeDao.class);
        usernameGenerator = mock(UsernameGenerator.class);
        passwordGenerator = mock(PasswordGenerator.class);

        service = new TrainerServiceImpl();
        service.setTrainerDao(trainerDao);
        service.setTraineeDao(traineeDao);
        service.setUsernameGenerator(usernameGenerator);
        service.setPasswordGenerator(passwordGenerator);
    }

    @Test
    void createTrainer_setsCredentialsAndSaves() {
        Trainer trainer = buildTrainer(1L, "Alice", "Green");

        when(trainerDao.findAll()).thenReturn(Collections.emptyList());
        when(traineeDao.findAll()).thenReturn(Collections.emptyList());
        when(usernameGenerator.generateUsername(eq("Alice"), eq("Green"), anySet()))
                .thenReturn("Alice.Green");
        when(passwordGenerator.generatePassword()).thenReturn("Pass123$%^");
        when(trainerDao.saveTrainer(trainer)).thenReturn(trainer);

        Trainer result = service.createTrainer(trainer);

        assertEquals("Alice.Green", result.getUsername());
        assertEquals("Pass123$%^", result.getPassword());
        verify(trainerDao).saveTrainer(trainer);
    }

    @Test
    void createTrainer_passesAllExistingUsernamesToGenerator() {
        Trainer trainer = buildTrainer(3L, "Bob", "Stone");
        Trainer existingTrainer = buildTrainer(1L, "Other", "Person");
        existingTrainer.setUsername("Other.Person");
        Trainee existingTrainee = new Trainee();
        existingTrainee.setUsername("Jane.Doe");

        when(trainerDao.findAll()).thenReturn(List.of(existingTrainer));
        when(traineeDao.findAll()).thenReturn(List.of(existingTrainee));
        when(usernameGenerator.generateUsername(eq("Bob"), eq("Stone"), anySet()))
                .thenAnswer(inv -> {
                    Set<String> passed = inv.getArgument(2);
                    assertTrue(passed.contains("Other.Person"), "Should include existing trainer username");
                    assertTrue(passed.contains("Jane.Doe"),     "Should include existing trainee username");
                    return "Bob.Stone";
                });
        when(passwordGenerator.generatePassword()).thenReturn("Xy9!zAb2qW");
        when(trainerDao.saveTrainer(trainer)).thenReturn(trainer);

        service.createTrainer(trainer);

        verify(usernameGenerator).generateUsername(eq("Bob"), eq("Stone"), anySet());
    }

    @Test
    void getTrainerById_delegatesToDao() {
        Trainer trainer = buildTrainer(10L, "Carol", "Black");
        when(trainerDao.getTrainerById(10L)).thenReturn(trainer);
        Trainer result = service.getTrainerById(10L);

        assertNotNull(result);
        assertEquals(10L, result.getUserId());
        assertEquals("Carol", result.getFirstName());
        verify(trainerDao).getTrainerById(10L);
    }

    @Test
    void updateTrainer_delegatesToDao() {
        Trainer trainer = buildTrainer(20L, "Dan", "White");
        when(trainerDao.updateTrainer(trainer)).thenReturn(trainer);
        Trainer result = service.updateTrainer(trainer);

        assertEquals(trainer, result);
        verify(trainerDao).updateTrainer(trainer);
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