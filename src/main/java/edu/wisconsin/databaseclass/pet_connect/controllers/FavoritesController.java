package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wisconsin.databaseclass.pet_connect.dtos.PetDTO;
import edu.wisconsin.databaseclass.pet_connect.entities.Favorites;
import edu.wisconsin.databaseclass.pet_connect.entities.FavoritesId;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.services.FavoritesService;
import edu.wisconsin.databaseclass.pet_connect.services.PetService;
import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;


    @Autowired
    private PetService petService;

    @PostMapping("/favorite")
    @ResponseBody
    public ResponseEntity<Favorites> saveFavorite(@RequestBody Map<String, String> payload, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            String petId = payload.get("petId");
            System.out.println("Received petId: " + petId);

            Pet pet = petService.getPetById(petId);
            if (pet == null) {
                System.out.println("Pet not found for petId: " + petId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Favorites favorites = new Favorites();
            favorites.setId(new FavoritesId(user.getUserId(), petId));
            favorites.setUser(user);
            favorites.setPet(pet);
            favorites.setFavoritedAt(new Date());

            Favorites savedFavorite = favoritesService.saveFavorites(favorites);
            System.out.println("Favorite saved: " + savedFavorite);

            return new ResponseEntity<>(savedFavorite, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/favorites")
    @ResponseBody
    public ResponseEntity<List<PetDTO>> getFavorites(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            List<Favorites> favorites = favoritesService.getFavoritesByUserId(user.getUserId());
            List<PetDTO> favoritePets = favorites.stream().map(fav -> {
                Pet pet = fav.getPet();
                PetDTO petDTO = new PetDTO();
                petDTO.setPetId(pet.getPetId());
                petDTO.setName(pet.getName());
                petDTO.setPhotoUrl("/petImage/" + pet.getPetId());
                petDTO.setAdoptionStatus(pet.getAdoptionStatus());
                petDTO.setFee(pet.getFee());
                return petDTO;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(favoritePets, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}