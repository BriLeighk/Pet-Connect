package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;
import edu.wisconsin.databaseclass.pet_connect.dtos.PetDTO;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class PetController {

    private static final Logger logger = Logger.getLogger(PetController.class.getName());

    @Autowired
    private PetService petService;


    @GetMapping("/addNewPet")
    public String showAddNewPetForm(Model model) {
        List<Breed> dogBreeds = petService.getBreedsByType(1);
        List<Breed> catBreeds = petService.getBreedsByType(2);
        List<Color> colors = petService.getAllColors();
        List<Location> locations = petService.getAllLocations();

        logger.info("Dog Breeds: " + dogBreeds);
        logger.info("Cat Breeds: " + catBreeds);
        logger.info("Colors: " + colors);
        logger.info("Locations: " + locations);

        model.addAttribute("dogBreeds", dogBreeds);
        model.addAttribute("catBreeds", catBreeds);
        model.addAttribute("colors", colors);
        model.addAttribute("locations", locations);
        return "addNewPet";
    }

    @GetMapping("/breeds")
    @ResponseBody
    public List<Breed> getBreedsByType(@RequestParam("type") int type) {
        return petService.getBreedsByType(type);
    }

    @GetMapping("/petImage/{petId}")
    @ResponseBody
    public ResponseEntity<byte[]> getPetImage(@PathVariable int petId) {
        Pet pet = petService.getPetById(petId);
        if (pet != null && pet.getPhotos() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(pet.getPhotos());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pet/{petId}")
    @ResponseBody
    public PetDTO getPetById(@PathVariable int petId) {
        Pet pet = petService.getPetById(petId);
        if (pet != null) {
            PetDTO petDTO = new PetDTO();
            petDTO.setPetId(pet.getPetId());
            petDTO.setName(pet.getName());
            petDTO.setType(pet.getType() == 1 ? "Dog" : "Cat");
            petDTO.setBreed1(petService.getBreedById(pet.getBreed1()).getName());
            if (pet.getBreed2() != null) {
                petDTO.setBreed2(petService.getBreedById(pet.getBreed2()).getName());
            }
            petDTO.setColor1(petService.getColorById(pet.getColor1()).getName());
            if (pet.getColor2() != null) {
                petDTO.setColor2(petService.getColorById(pet.getColor2()).getName());
            }
            if (pet.getColor3() != null) {
                petDTO.setColor3(petService.getColorById(pet.getColor3()).getName());
            }
            petDTO.setLocation(petService.getLocationById(pet.getLocation().getLocationId()).getState());
            petDTO.setMaturitySize(getMaturitySizeString(pet.getMaturitySize()));
            petDTO.setAge(pet.getAge());
            petDTO.setGender(pet.getGender() == 1 ? "Male" : "Female");
            petDTO.setAdoptionStatus(pet.getAdoptionStatus());
            petDTO.setHealthStatus(getHealthStatusString(pet.getHealthStatus()));
            petDTO.setVaccinationStatus(getVaccinationStatusString(pet.getVaccinationStatus()));
            petDTO.setSterilized(getSterilizedString(pet.getSterilized()));
            petDTO.setDewormed(getDewormedString(pet.getDewormed()));
            petDTO.setFee(pet.getFee());
            petDTO.setFurLength(getFurLengthString(pet.getFurLength()));
            petDTO.setDescription(pet.getDescription() != null ? pet.getDescription() : "");
            petDTO.setPhotoUrl("/petImage/" + pet.getPetId());
            return petDTO;
        }
        return null;
    }

    private String getMaturitySizeString(int maturitySize) {
        switch (maturitySize) {
            case 1: return "Small";
            case 2: return "Medium";
            case 3: return "Large";
            case 4: return "Extra Large";
            default: return "Not Specified";
        }
    }

    private String getHealthStatusString(int healthStatus) {
        switch (healthStatus) {
            case 1: return "Healthy";
            case 2: return "Minor Injury";
            case 3: return "Serious Injury";
            default: return "Not Specified";
        }
    }

    private String getVaccinationStatusString(int vaccinationStatus) {
        switch (vaccinationStatus) {
            case 1: return "Vaccinated";
            case 2: return "Not Vaccinated";
            default: return "Not Sure";
        }
    }

    private String getSterilizedString(int sterilized) {
        switch (sterilized) {
            case 1: return "Sterilized";
            case 2: return "Not Sterilized";
            default: return "Not Sure";
        }
    }

    private String getDewormedString(int dewormed) {
        switch (dewormed) {
            case 1: return "Dewormed";
            case 2: return "Not Dewormed";
            default: return "Not Sure";
        }
    }

    private String getFurLengthString(int furLength) {
        switch (furLength) {
            case 1: return "Short";
            case 2: return "Medium";
            case 3: return "Long";
            default: return "Not Specified";
        }
    }
}