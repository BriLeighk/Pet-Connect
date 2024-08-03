package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String home(Model model) {
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        model.addAttribute("breedNames", pets.stream()
                .collect(Collectors.toMap(Pet::getPetId, pet -> petService.getBreedById(pet.getBreed1()).getName())));
        return "home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        model.addAttribute("breedNames", pets.stream()
                .collect(Collectors.toMap(Pet::getPetId, pet -> petService.getBreedById(pet.getBreed1()).getName())));
        return "home";
    }
}