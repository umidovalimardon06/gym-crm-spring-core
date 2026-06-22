package gym.service.impl;

import gym.dao.TrainerDao;
import gym.domain.Trainer;
import gym.service.TrainerService;
import gym.utility.PasswordGenerator;
import gym.utility.UsernameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrainerServiceImpl implements TrainerService {

    private static final Logger log = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private TrainerDao trainerDao;
    private TrainerDao traineeDao;
    private UsernameGenerator usernameGenerator;
    private PasswordGenerator passwordGenerator;

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Autowired
    public void setTraineeDao(TrainerDao traineeDao) {
        this.traineeDao = traineeDao;
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
    public Trainer createTrainer(Trainer trainer) {
        log.info("Creating trainer: {} {}", trainer.getFirstName(), trainer.getLastName());

        Set<String> existingUsernames = collectAllUsernames();
        String username = usernameGenerator.generateUsername(
                trainer.getFirstName(), trainer.getLastName(), existingUsernames);
        String password = passwordGenerator.generatePassword();

        trainer.setUsername(username);
        trainer.setPassword(password);

        Trainer saved = trainerDao.saveTrainer(trainer);
        log.info("Created trainer with id={}, username={}", saved.getUserId(), saved.getUsername());
        return saved;
    }

    @Override
    public Trainer getTrainerById(Long id) {
        log.info("Fetching trainer with id={}", id);
        return trainerDao.getTrainerById(id);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        log.info("Updating trainer with id={}", trainer.getUserId());
        return trainerDao.updateTrainer(trainer);
    }

    private Set<String> collectAllUsernames() {
        Set<String> usernames = new HashSet<>();
        traineeDao.findAll().forEach(t -> usernames.add(t.getUsername()));
        trainerDao.findAll().forEach(t -> usernames.add(t.getUsername()));
        return usernames;
    }
}