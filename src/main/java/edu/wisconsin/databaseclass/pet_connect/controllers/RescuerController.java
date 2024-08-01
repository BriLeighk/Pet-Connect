package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Controller
public class RescuerController {

    private static final Logger logger = LoggerFactory.getLogger(RescuerController.class);

    private final PetService petService;

    public RescuerController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping({"/rescuerDashboard"})
    public String showRescuerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRescuer() == null) {
            return "redirect:/login"; // Redirect to login if user is not in session or not a rescuer
        }
        Integer rescuerId = user.getRescuer().getRescuerId();
        logger.info("Rescuer ID: " + rescuerId);
        model.addAttribute("rescuerId", rescuerId);
        model.addAttribute("user", user); // Add user to the model
        model.addAttribute("pets", petService.getPetsByRescuer(rescuerId)); // Add pets to the model
        if (user.getProfileImage() == null) {
            model.addAttribute("profileImage", "/images/profile-placeholder.png");
        } else {
            model.addAttribute("profileImage", "/profileImage/" + user.getUserId());
        }
        // Add breeds, colors, and locations to the model
        model.addAttribute("dogBreeds", petService.getBreedsByType(1));
        model.addAttribute("catBreeds", petService.getBreedsByType(2));
        model.addAttribute("colors", petService.getAllColors());
        model.addAttribute("locations", petService.getAllLocations());
        return "rescuerDashboard";
    }

    @PostMapping("/rescuerDashboard")
    public String savePet(@RequestParam("name") String name, @RequestParam("breed1") int breed1,
                          @RequestParam(value = "breed2", required = false) Integer breed2, @RequestParam("type") int type,
                          @RequestParam("maturitySize") int maturitySize, @RequestParam("age") int age,
                          @RequestParam("gender") int gender, @RequestParam("adoptionStatus") String adoptionStatus,
                          @RequestParam("color1") int color1, @RequestParam(value = "color2", required = false) Integer color2,
                          @RequestParam(value = "color3", required = false) Integer color3, @RequestParam("healthStatus") int healthStatus,
                          @RequestParam("vaccinationStatus") int vaccinationStatus, @RequestParam("sterilized") int sterilized,
                          @RequestParam("dewormed") int dewormed, @RequestParam(value = "fee", defaultValue = "0") double fee,
                          @RequestParam("furLength") int furLength, @RequestParam("location") int locationId,
                          @RequestParam("rescuerId") int rescuerId, @RequestParam("photos") MultipartFile[] photos) {
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
        pet.setVideoAmount(0); // Set default value
        pet.setPhotoAmount(photos != null ? photos.length : 0); // Set photo amount based on uploaded photos
        Location location = petService.getLocationById(locationId);
        pet.setLocation(location);
        Rescuer rescuer = petService.getRescuerById(rescuerId);
        pet.setRescuer(rescuer);

        try {
            if (photos != null && photos.length > 0) {
                // Assuming you want to store the first photo for simplicity
                pet.setPhotos(photos[0].getBytes());
            }
        } catch (IOException e) {
            logger.error("Failed to upload photos", e);
        }

        petService.savePet(pet);
        return "redirect:/rescuerDashboard";
    }
}