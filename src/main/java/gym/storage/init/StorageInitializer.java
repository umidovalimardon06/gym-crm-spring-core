package gym.storage.init;

import gym.domain.Trainee;
import gym.domain.Trainer;
import gym.domain.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StorageInitializer implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(StorageInitializer.class);

    private static final String TRAINEE_BEAN  = "traineeStorage";
    private static final String TRAINER_BEAN  = "trainerStorage";
    private static final String TRAINING_BEAN = "trainingStorage";

    private DataFileParser parser;
    private Resource traineeData;
    private Resource trainerData;
    private Resource trainingData;

    @Autowired
    public void setParser(DataFileParser parser) {
        this.parser = parser;
    }

    @Autowired
    public void setTraineeData(@Value("${storage.data.trainees}") Resource traineeData) {
        this.traineeData = traineeData;
    }

    @Autowired
    public void setTrainerData(@Value("${storage.data.trainers}") Resource trainerData) {
        this.trainerData = trainerData;
    }

    @Autowired
    public void setTrainingData(@Value("${storage.data.trainings}") Resource trainingData) {
        this.trainingData = trainingData;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        switch (beanName) {
            case TRAINEE_BEAN  -> populateTrainees((Map<Long, Trainee>) bean);
            case TRAINER_BEAN  -> populateTrainers((Map<Long, Trainer>) bean);
            case TRAINING_BEAN -> populateTrainings((Map<Long, Training>) bean);
            default            -> {}
        }
        return bean;
    }

    private void populateTrainees(Map<Long, Trainee> storage) {
        log.info("Initializing trainee storage from {}", traineeData.getDescription());
        List<Trainee> trainees = parser.parseTrainees(traineeData);
        trainees.forEach(t -> storage.put(t.getUserId(), t));
        log.info("Trainee storage initialized with {} records", trainees.size());
    }

    private void populateTrainers(Map<Long, Trainer> storage) {
        log.info("Initializing trainer storage from {}", trainerData.getDescription());
        List<Trainer> trainers = parser.parseTrainers(trainerData);
        trainers.forEach(t -> storage.put(t.getUserId(), t));
        log.info("Trainer storage initialized with {} records", trainers.size());
    }

    private void populateTrainings(Map<Long, Training> storage) {
        log.info("Initializing training storage from {}", trainingData.getDescription());
        List<Training> trainings = parser.parseTrainings(trainingData);
        trainings.forEach(t -> storage.put(t.getId(), t));
        log.info("Training storage initialized with {} records", trainings.size());
    }
}