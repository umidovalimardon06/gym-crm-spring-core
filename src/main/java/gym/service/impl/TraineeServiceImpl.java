package gym.service.impl;

import gym.dao.TraineeDao;
import gym.dao.TrainerDao;
import gym.domain.Trainee;
import gym.service.TraineeService;
import gym.utility.PasswordGenerator;
import gym.utility.UsernameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger log = LoggerFactory.getLogger(TraineeServiceImpl.class);
    private TraineeDao traineeDao;
    private TrainerDao trainerDao;
    private UsernameGenerator usernameGenerator;
    private PasswordGenerator passwordGenerator;

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Autowired
    public void setUsernameGenerator(UsernameGenerator usernameGenerator) {
        this.usernameGenerator = usernameGenerator;
    }

    @Autowired
    public void setPasswordGenerator(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        log.info("Creating trainee: {} {}", trainee.getFirstName(), trainee.getLastName());

        Set<String> existingUsernames = collectAllUsernames();
        String username = usernameGenerator.generateUsername(
                trainee.getFirstName(), trainee.getLastName(), existingUsernames);
        String password = passwordGenerator.generatePassword();

        trainee.setUsername(username);
        trainee.setPassword(password);

        Trainee saved = traineeDao.saveTrainee(trainee);
        log.info("Created trainee with id={}, username={}", saved.getUserId(), saved.getUsername());
        return saved;
    }

    @Override
    public Trainee getTraineeById(Long id) {
        log.info("Fetching trainee with id={}", id);
        return traineeDao.getTraineeById(id);
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        log.info("Updating trainee with id={}", trainee.getUserId());
        traineeDao.updateTrainee(trainee);
        return trainee;
    }

    @Override
    public void deleteTrainee(Long id) {
        log.info("Deleting trainee with id={}", id);
        traineeDao.deleteTraineeById(id);
    }

    private Set<String> collectAllUsernames() {
        Set<String> usernames = new HashSet<>();
        traineeDao.findAll().forEach(t -> usernames.add(t.getUsername()));
        trainerDao.findAll().forEach(t -> usernames.add(t.getUsername()));
        return usernames;
    }
}