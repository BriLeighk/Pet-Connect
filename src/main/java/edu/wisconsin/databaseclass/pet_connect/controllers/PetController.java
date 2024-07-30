package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;

@Controller
public class PetController {

    @Autowired
    private PetService petService;

    // maps to pets --- may need modification
    @GetMapping("/pets")
    public String getAllPets(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "pets";
    }

    //search for pets by ID
    @GetMapping("/pets/{id}")
    public String searchPets(Model model, @PathVariable int id) {
        model.addAttribute("pets", petService.getPetById(id));
        return "pets";
    }

    @PostMapping("/pet")
    public String savePet(@RequestParam("name") String name, @RequestParam("breed") String breed,
                          @RequestParam("type") String type, @RequestParam("age") int age) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setBreed(breed);
        pet.setType(type);
        pet.setAge(age);
        petService.savePet(pet);
        return "redirect:/pets";
    }
}

