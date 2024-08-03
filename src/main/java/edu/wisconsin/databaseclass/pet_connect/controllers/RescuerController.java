package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RescuerController {

    private static final Logger logger = LoggerFactory.getLogger(RescuerController.class);

    private final PetService petService;

    public RescuerController(PetService petService) {
        this.petService = petService;
    }

    // endpoint to show rescuer dashboard
    @GetMapping({"/rescuerDashboard"})
    public String showRescuerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRescuer() == null) {
            return "redirect:/login"; // Redirect to login if user is not in session or not a rescuer
        }
        String rescuerId = user.getRescuer().getRescuerId();
        logger.info("Rescuer ID: " + rescuerId);
        model.addAttribute("rescuerId", rescuerId.toString());
        model.addAttribute("user", user); // Add user to the model
        model.addAttribute("pets", petService.getPetsByRescuer(user.getRescuer())); // Add pets to the model
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
    
}