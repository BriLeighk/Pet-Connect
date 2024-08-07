package edu.wisconsin.databaseclass.pet_connect.utils;

import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.repositories.BreedRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.ColorRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.LocationRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.PetRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.RescuerRepository;

@Component
public class CSVToDatabase implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CSVToDatabase.class);

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RescuerRepository rescuerRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public void run(String... args) throws Exception {
        String csvFile = "src/main/resources/static/test.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] values;
            reader.readNext(); // Skip header
            while ((values = reader.readNext()) != null) {
                Pet pet = new Pet();
                pet.setFieldsFromCsv(values, locationRepository, rescuerRepository, breedRepository, colorRepository);
                pet.setLongitude(32.715736); // Default value
                pet.setLatitude(-117.161087);  // Default value
                petRepository.save(pet);
                logger.info("Saved pet with ID: {}", pet.getPetId());
            }
        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading CSV file", e);
        }
    }
}