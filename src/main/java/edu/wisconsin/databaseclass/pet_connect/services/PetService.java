package edu.wisconsin.databaseclass.pet_connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.repositories.PetRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.RescuerRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.LocationRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.BreedRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.ColorRepository;

import java.util.List;

@Service
public class PetService {

    // Injections to interact with the database
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private RescuerRepository rescuerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private BreedService breedService;

    @Autowired
    private ColorService colorService;

    // endpoint to get all pets
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // endpoint to get pet by id
    public Pet getPetById(int petId) {
        return petRepository.findById(petId).orElse(null);
    }

    // endpoint to save pet to database
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    // endpoint to delete pet by id
    public void deletePet(int petId) {
        petRepository.deleteById(petId);
    }

    // endpoint to get pets by rescuer
    public List<Pet> getPetsByRescuer(int rescuerId) {
        Rescuer rescuer = rescuerRepository.findById(rescuerId).orElse(null);
        if (rescuer != null) {
            return petRepository.findByRescuer(rescuer);
        }
        return List.of();
    }

    // endpoint to get rescuer by id
    public Rescuer getRescuerById(int rescuerId) {
        return rescuerRepository.findById(rescuerId).orElse(null);
    }

    // endpoint to get location by id
    public Location getLocationById(int locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    // endpoint to get breeds by type
    public List<Breed> getBreedsByType(int type) {
        return breedRepository.findByType(type);
    }

    // endpoint to get all colors
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    // endpoint to get all locations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // endpoint to get breed by id
    public Breed getBreedById(int id) {
        return breedService.getBreedById(id);
    }

    // endpoint to get color by id
    public Color getColorById(int id) {
        return colorService.getColorById(id);
    }

    // Method to delete a pet by its ID
    public void deletePetById(Integer petId) {
        petRepository.deleteById(petId); // Calls the repository method to delete the pet
    }
}