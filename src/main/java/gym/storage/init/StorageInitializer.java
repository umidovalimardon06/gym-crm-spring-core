package gym.storage.init;

import gym.domain.Trainee;
import gym.domain.Trainer;
import gym.domain.Training;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StorageInitializer implements BeanPostProcessor {

    private static final String TRAINEE_BEAN = "traineeStorage";
    private static final String TRAINER_BEAN = "trainerStorage";
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
            default            -> { /* not a storage bean — ignore */ }
        }
        return bean;
    }

    private void populateTrainees(Map<Long, Trainee> storage) {
        parser.parseTrainees(traineeData)
                .forEach(t -> storage.put(t.getUserId(), t));
    }

    private void populateTrainers(Map<Long, Trainer> storage) {
        parser.parseTrainers(trainerData)
                .forEach(t -> storage.put(t.getUserId(), t));
    }

    private void populateTrainings(Map<Long, Training> storage) {
        parser.parseTrainings(trainingData)
                .forEach(t -> storage.put(t.getId(), t));
    }
}