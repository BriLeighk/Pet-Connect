package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;
import edu.wisconsin.databaseclass.pet_connect.dtos.PetDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Controller
public class PetController {

    @Autowired
    private PetService petService;

    // endpoint to show add new pet form
    @GetMapping("/addNewPet")
    public String showAddNewPetForm(Model model) {
        List<Breed> dogBreeds = petService.getBreedsByType(1);
        List<Breed> catBreeds = petService.getBreedsByType(2);
        List<Color> colors = petService.getAllColors();
        List<Location> locations = petService.getAllLocations();

        model.addAttribute("dogBreeds", dogBreeds);
        model.addAttribute("catBreeds", catBreeds);
        model.addAttribute("colors", colors);
        model.addAttribute("locations", locations);
        return "addNewPet";
    }

    // endpoint to get breeds by type
    @GetMapping("/breeds")
    @ResponseBody
    public List<Breed> getBreedsByType(@RequestParam("type") int type) {
        return petService.getBreedsByType(type);
    }

    // endpoint to get pet image
    @GetMapping("/petImage/{petId}")
    @ResponseBody
    public ResponseEntity<byte[]> getPetImage(@PathVariable String petId) {
        Pet pet = petService.getPetById(petId);
        if (pet != null && pet.getPhotos() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(pet.getPhotos());
        } else {
            try {
                byte[] placeholderImage = Files.readAllBytes(Paths.get("src/main/resources/static/images/pet-placeholder.png"));
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(placeholderImage);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    // endpoint to get pet by id
    @GetMapping("/pet/{petId}")
    @ResponseBody
    public ResponseEntity<PetDTO> getPetById(@PathVariable String petId) {
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
            return ResponseEntity.ok(petDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    // endpoint to delete pet by id
    @DeleteMapping("/deletePet/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable String petId) {
        try {
            petService.deletePetById(petId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // helper function to get maturity size string
    private String getMaturitySizeString(int maturitySize) {
        switch (maturitySize) {
            case 1: return "Small";
            case 2: return "Medium";
            case 3: return "Large";
            case 4: return "Extra Large";
            default: return "Not Specified";
        }
    }

    // helper function to get health status string
    private String getHealthStatusString(int healthStatus) {
        switch (healthStatus) {
            case 1: return "Healthy";
            case 2: return "Minor Injury";
            case 3: return "Serious Injury";
            default: return "Not Specified";
        }
    }

    // helper function to get vaccination status string
    private String getVaccinationStatusString(int vaccinationStatus) {
        switch (vaccinationStatus) {
            case 1: return "Vaccinated";
            case 2: return "Not Vaccinated";
            default: return "Not Sure";
        }
    }

    // helper function to get sterilized string
    private String getSterilizedString(int sterilized) {
        switch (sterilized) {
            case 1: return "Sterilized";
            case 2: return "Not Sterilized";
            default: return "Not Sure";
        }
    }

    // helper function to get dewormed string
    private String getDewormedString(int dewormed) {
        switch (dewormed) {
            case 1: return "Dewormed";
            case 2: return "Not Dewormed";
            default: return "Not Sure";
        }
    }

    // helper function to get fur length string
    private String getFurLengthString(int furLength) {
        switch (furLength) {
            case 1: return "Short";
            case 2: return "Medium";
            case 3: return "Long";
            default: return "Not Specified";
        }
    }

    // endpoint to add pet
    @PostMapping("/addPet")
    public String addPet(@RequestParam("name") String name, @RequestParam("breed1") int breed1,
                          @RequestParam(value = "breed2", required = false) Integer breed2, @RequestParam("type") int type,
                          @RequestParam("maturitySize") int maturitySize, @RequestParam("age") int age,
                          @RequestParam("gender") int gender, @RequestParam("adoptionStatus") String adoptionStatus,
                          @RequestParam("color1") int color1, @RequestParam(value = "color2", required = false) Integer color2,
                          @RequestParam(value = "color3", required = false) Integer color3, @RequestParam("healthStatus") int healthStatus,
                          @RequestParam("vaccinationStatus") int vaccinationStatus, @RequestParam("sterilized") int sterilized,
                          @RequestParam("dewormed") int dewormed, @RequestParam(value = "fee", defaultValue = "0") double fee,
                          @RequestParam("furLength") int furLength, @RequestParam("location") int locationId,
                          @RequestParam("rescuerId") String rescuerId, @RequestParam("description") String description, // Add description
                          @RequestParam("photos") MultipartFile[] photos) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setBreed1(breed1);
        pet.setBreed2(breed2);
        pet.setType(type);
        pet.setMaturitySize(maturitySize);
        pet.setAge(age);
        pet.setGender(gender);
        pet.setAdoptionStatus(adoptionStatus);
        pet.setColor1(color1);
        pet.setColor2(color2);
        pet.setColor3(color3);
        pet.setHealthStatus(healthStatus);
        pet.setVaccinationStatus(vaccinationStatus);
        pet.setSterilized(sterilized);
        pet.setDewormed(dewormed);
        pet.setFee(fee);
        pet.setFurLength(furLength);
        pet.setDescription(description); // Set description
        pet.setPhotoAmount(photos != null ? photos.length : 0); // Set photo amount based on uploaded photos
        Location location = petService.getLocationById(locationId);
        pet.setLocation(location);
        Rescuer rescuer = petService.getRescuerById(rescuerId);
        pet.setRescuer(rescuer);

        try {
            if (photos != null && photos.length > 0) {
                pet.setPhotos(photos[0].getBytes());
            }
        } catch (IOException e) {
            // Handle exception
        }

        // save pet to database
        petService.savePet(pet);
        return "redirect:/rescuerDashboard";
    }

    // endpoint to update (edit) pet
    @PostMapping("/updatePet")
    public String updatePet(@RequestParam("petId") String petId,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "fee", required = false) Double fee,
                            @RequestParam(value = "adoptionStatus", required = false) String adoptionStatus,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "maturitySize", required = false) Integer maturitySize,
                            @RequestParam(value = "age", required = false) Integer age,
                            @RequestParam(value = "healthStatus", required = false) Integer healthStatus,
                            @RequestParam(value = "vaccinationStatus", required = false) Integer vaccinationStatus,
                            @RequestParam(value = "sterilized", required = false) Integer sterilized,
                            @RequestParam(value = "dewormed", required = false) Integer dewormed,
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            @RequestParam("clearImageFlag") boolean clearImageFlag) {
        Pet pet = petService.getPetById(petId);
        if (pet != null) {
            if (name != null) pet.setName(name);
            if (fee != null) pet.setFee(fee);
            if (adoptionStatus != null) pet.setAdoptionStatus(adoptionStatus);
            if (description != null) pet.setDescription(description);
            if (maturitySize != null) pet.setMaturitySize(maturitySize);
            if (age != null) pet.setAge(age);
            if (healthStatus != null) pet.setHealthStatus(healthStatus);
            if (vaccinationStatus != null) pet.setVaccinationStatus(vaccinationStatus);
            if (sterilized != null) pet.setSterilized(sterilized);
            if (dewormed != null) pet.setDewormed(dewormed);
            if (clearImageFlag) {
                pet.setPhotos(null);
            } else if (file != null && !file.isEmpty()) {
                try {
                    pet.setPhotos(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            petService.savePet(pet);
        }
        return "redirect:/rescuerDashboard";
    }
}