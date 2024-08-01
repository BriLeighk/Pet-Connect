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

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(int petId) {
        return petRepository.findById(petId).orElse(null);
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletePet(int petId) {
        petRepository.deleteById(petId);
    }

    public List<Pet> getPetsByRescuer(int rescuerId) {
        Rescuer rescuer = rescuerRepository.findById(rescuerId).orElse(null);
        if (rescuer != null) {
            return petRepository.findByRescuer(rescuer);
        }
        return List.of();
    }

    public Rescuer getRescuerById(int rescuerId) {
        return rescuerRepository.findById(rescuerId).orElse(null);
    }

    public Location getLocationById(int locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    public List<Breed> getBreedsByType(int type) {
        return breedRepository.findByType(type);
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Breed getBreedById(int id) {
        return breedService.getBreedById(id);
    }

    public Color getColorById(int id) {
        return colorService.getColorById(id);
    }
}