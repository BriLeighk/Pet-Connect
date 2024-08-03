package edu.wisconsin.databaseclass.pet_connect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import edu.wisconsin.databaseclass.pet_connect.repositories.BreedRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.ColorRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.LocationRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.PetRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.RescuerRepository;

@Service
public class PetService {

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

    public Breed getBreedById(int breed1) {
        return breedRepository.findById(breed1).orElse(null);
    }

    public Color getColorById(int color1) {
        return colorRepository.findById(color1).orElse(null);
    }

    public Location getLocationById(int locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    public Rescuer getRescuerById(String rescuerId) {
        return rescuerRepository.findById(rescuerId).orElse(null);
    }

    public void savePet(Pet pet) {
        petRepository.save(pet);
    }

    public Pet getPetById(String petId) {
        return petRepository.findById(petId).orElse(null);
    }

    public void deletePetById(String petId) {
        petRepository.deleteById(petId);
    }

    public List<Breed> getBreedsByType(int type) {
        return breedRepository.findByType(type);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public List<Pet> getPetsByRescuer(Rescuer rescuerId) {
        return petRepository.findByRescuer(rescuerId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}