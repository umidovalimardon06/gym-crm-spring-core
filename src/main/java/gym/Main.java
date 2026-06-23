package gym;

import gym.config.AppConfig;
import gym.facade.GymFacade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        GymFacade gymFacade = context.getBean(GymFacade.class);

        gymFacade.getAllTraining().forEach(training -> {
            System.out.println("Training ID: " + training.getId());
            System.out.println("Trainee: " + training.getTraineeId());
            System.out.println("Trainer: " + training.getTrainerId());
            System.out.println("Date: " + training.getTrainingDate());
            System.out.println("Duration: " + training.getTrainingDuration());
            System.out.println("---------------------------");
        });
    }
}