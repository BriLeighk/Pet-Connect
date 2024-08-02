package edu.wisconsin.databaseclass.pet_connect.services;

import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.repositories.BreedRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.ColorRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.LocationRepository;
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

    @PostConstruct
    public void populateDatabase() {
        populateBreeds();
        populateColors();
        populateLocations();
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
}