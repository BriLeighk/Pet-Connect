package edu.wisconsin.databaseclass.pet_connect.services;

import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import edu.wisconsin.databaseclass.pet_connect.repositories.BreedRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.ColorRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.LocationRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.RescuerRepository;
import edu.wisconsin.databaseclass.pet_connect.utils.CsvReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabasePopulatorService {

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RescuerRepository rescuerRepository;

    @PostConstruct
    public void populateDatabase() {
        if (breedRepository.count() == 0) {
            populateBreeds();
        }
        if (colorRepository.count() == 0) {
            populateColors();
        }
        if (locationRepository.count() == 0) {
            populateLocations();
        }
        if (rescuerRepository.count() == 0) {
            populateRescuers();
        }
    }

    private void populateBreeds() {
        List<Breed> breeds = CsvReader.readBreedsCsv("/static/BreedLabels.csv");
        breedRepository.saveAll(breeds);
    }

    private void populateColors() {
        List<Color> colors = CsvReader.readColorsCsv("/static/ColorLabels.csv");
        colorRepository.saveAll(colors);
    }

    private void populateLocations() {
        List<Location> locations = CsvReader.readLocationsCsv("/static/StateLabels.csv");
        locationRepository.saveAll(locations);
    }

    private void populateRescuers() {
        List<Rescuer> rescuers = CsvReader.readRescuersCsv("/static/RescuerLabels.csv");
        rescuerRepository.saveAll(rescuers);
    }
}