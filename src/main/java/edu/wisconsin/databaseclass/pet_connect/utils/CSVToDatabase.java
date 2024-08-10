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
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
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
                
                // Check and modify breed1 if it is 0
                int breed1 = Integer.parseInt(values[3]); // breed1 is at index 3
                if (breed1 == 0) {
                    breed1 = 307; // Change breed1 to 307 if it is 0
                }
                values[3] = String.valueOf(breed1); // Update the value in the array
                
                // Check rescuer ID
                String rescuerId = values[17]; // rescuer_ID is at index 17
                if (!rescuerRepository.existsById(rescuerId)) {
                    // add the rescuer to the database if it doesn't exist
                    Rescuer newRescuer = new Rescuer();
                    newRescuer.setRescuerId(rescuerId);
                    // Set other fields if necessary
                    rescuerRepository.save(newRescuer);
                }
                
                pet.setFieldsFromCsv(values, locationRepository, rescuerRepository, breedRepository, colorRepository);
                
                // Set longitude and latitude from the location entity
                if (pet.getLocation() != null) {
                    pet.setLongitude(pet.getLocation().getLongitude());
                    pet.setLatitude(pet.getLocation().getLatitude());
                }
                petRepository.save(pet);
                logger.info("Saved pet with ID: {}", pet.getPetId());
            }
        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading CSV file", e);
        }
    }
}