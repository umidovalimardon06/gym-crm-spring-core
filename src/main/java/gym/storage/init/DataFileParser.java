package gym.storage.init;

import gym.domain.Trainee;
import gym.domain.Trainer;
import gym.domain.Training;
import gym.domain.TrainingType;
import gym.storage.exception.StorageInitializationException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataFileParser {
    private static final String CSV_DELIMITER = ",";

    public List<Trainee> parseTrainees(Resource resource) {
        return readDataRows(resource).stream()
                .map(this::toTrainee)
                .collect(Collectors.toList());
    }

    public List<Trainer> parseTrainers(Resource resource) {
        return readDataRows(resource).stream()
                .map(this::toTrainer)
                .collect(Collectors.toList());
    }

    public List<Training> parseTrainings(Resource resource) {
        return readDataRows(resource).stream()
                .map(this::toTraining)
                .collect(Collectors.toList());
    }


    private List<String[]> readDataRows(Resource resource) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {           // skip header
                    isFirstLine = false;
                    continue;
                }
                if (line.isBlank()) continue;

                rows.add(line.split(CSV_DELIMITER, -1));
            }

        } catch (IOException e) {
            throw new StorageInitializationException(
                    "Failed to read resource: " + resource.getDescription(), e);
        }

        return rows;
    }


    private Trainee toTrainee(String[] row) {
        try {
            Trainee t = new Trainee();
            t.setUserId(Long.parseLong(row[0].trim()));
            t.setFirstName(row[1].trim());
            t.setLastName(row[2].trim());
            t.setUsername(row[3].trim());
            t.setPassword(row[4].trim());
            t.setActive(Boolean.parseBoolean(row[5].trim()));
            t.setDateOfBirth(LocalDate.parse(row[6].trim()));
            t.setAddress(row[7].trim());
            return t;
        } catch (Exception e) {
            throw new StorageInitializationException(
                    "Failed to parse trainee row: " + String.join(",", row), e);
        }
    }

    private Trainer toTrainer(String[] row) {
        try {
            Trainer t = new Trainer();
            t.setUserId(Long.parseLong(row[0].trim()));
            t.setFirstName(row[1].trim());
            t.setLastName(row[2].trim());
            t.setUsername(row[3].trim());
            t.setPassword(row[4].trim());
            t.setActive(Boolean.parseBoolean(row[5].trim()));
            t.setSpecialization(TrainingType.valueOf(row[6].trim().toUpperCase()));
            return t;
        } catch (Exception e) {
            throw new StorageInitializationException(
                    "Failed to parse trainer row: " + String.join(",", row), e);
        }
    }

    private Training toTraining(String[] row) {
        try {
            Training t = new Training();
            t.setId(Long.parseLong(row[0].trim()));
            t.setTraineeId(Long.parseLong(row[1].trim()));
            t.setTrainerId(Long.parseLong(row[2].trim()));
            t.setTrainingName(row[3].trim());
            t.setTrainingType(TrainingType.valueOf(row[4].trim().toUpperCase()));
            t.setTrainingDate(LocalDate.parse(row[5].trim()));
            t.setTrainingDuration(Duration.ofMinutes(Long.parseLong(row[6].trim())));
            return t;
        } catch (Exception e) {
            throw new StorageInitializationException(
                    "Failed to parse training row: " + String.join(",", row), e);
        }
    }
}