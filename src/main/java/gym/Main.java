package gym;

import gym.config.AppConfig;
import gym.domain.Trainee;
import gym.domain.Trainer;
import gym.domain.Training;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            @SuppressWarnings("unchecked")
            Map<Long, Trainee> trainees = ctx.getBean("traineeStorage", Map.class);
            @SuppressWarnings("unchecked")
            Map<Long, Trainer> trainers = ctx.getBean("trainerStorage", Map.class);
            @SuppressWarnings("unchecked")
            Map<Long, Training> trainings = ctx.getBean("trainingStorage", Map.class);

            System.out.println("\n=== Loaded Trainees (" + trainees.size() + ") ===");
            trainees.values().forEach(System.out::println);

            System.out.println("\n=== Loaded Trainers (" + trainers.size() + ") ===");
            trainers.values().forEach(System.out::println);

            System.out.println("\n=== Loaded Trainings (" + trainings.size() + ") ===");
            trainings.values().forEach(System.out::println);
        }
    }
}