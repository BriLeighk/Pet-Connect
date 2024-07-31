package edu.wisconsin.databaseclass.pet_connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import edu.wisconsin.databaseclass.pet_connect.repositories.PetRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.RescuerRepository;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

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
    @Autowired
    private RescuerRepository rescuerRepository;

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
}