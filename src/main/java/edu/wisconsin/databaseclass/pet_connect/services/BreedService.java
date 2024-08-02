package edu.wisconsin.databaseclass.pet_connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.repositories.BreedRepository;

@Service
public class BreedService {

    @Autowired
    private BreedRepository breedRepository;

    public Breed getBreedById(int id) {
        return breedRepository.findById(id).orElse(null);
    }
}