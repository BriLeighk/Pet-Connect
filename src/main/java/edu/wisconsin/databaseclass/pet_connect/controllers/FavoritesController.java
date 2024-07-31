package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wisconsin.databaseclass.pet_connect.entities.Favorites;
import edu.wisconsin.databaseclass.pet_connect.entities.FavoritesId;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.services.FavoritesService;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;
import edu.wisconsin.databaseclass.pet_connect.services.UserService;

import java.util.Date;

@Controller
public class FavoritesController { // Controller for Favorites relationship set

    @Autowired
    private FavoritesService favoritesService; // access to favorites service file

    @Autowired
    private UserService userService; // access to user service file

    @Autowired
    private PetService petService; // access to pet service file

    // boilerplate code --- may need modification once we integrate pet profiles feature
    @GetMapping("/favorites")
    public String getAllFavorites(Model model) {
        model.addAttribute("favorites", favoritesService.getAllFavorites());
        return "favorites";
    }

    @PostMapping("/favorite")
    public String saveFavorite(@RequestParam("user_ID") int userId, @RequestParam("pet_ID") int petId) {
        Favorites favorites = new Favorites();
        FavoritesId id = new FavoritesId(userId, petId);
        User user = userService.getUserById(userId);
        Pet pet = petService.getPetById(petId);
        favorites.setId(id);
        favorites.setUser(user);
        favorites.setPet(pet);
        favorites.setFavoritedAt(new Date());
        favoritesService.saveFavorites(favorites);
        return "redirect:/favorites";
    }
}






