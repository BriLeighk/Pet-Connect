package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
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

    @GetMapping("/pets/home")
    public String getHomePage(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "home";
    }

    @PostMapping("/pet")
    public String savePet(@RequestParam("name") String name, @RequestParam("breed1") int breed1,
                          @RequestParam("breed2") Integer breed2, @RequestParam("type") int type,
                          @RequestParam("age") int age, @RequestParam("rescuerId") int rescuerId) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setBreed1(breed1);
        pet.setBreed2(breed2);
        pet.setType(type);
        pet.setAge(age);
        Rescuer rescuer = petService.getRescuerById(rescuerId);
        pet.setRescuer(rescuer);
        petService.savePet(pet);
        return "redirect:/pets";
    }
}