package edu.wisconsin.databaseclass.pet_connect.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.Favorites;
import edu.wisconsin.databaseclass.pet_connect.entities.FavoritesId;
import edu.wisconsin.databaseclass.pet_connect.repositories.FavoritesRepository;

import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    public List<Favorites> getAllFavorites() {
        return favoritesRepository.findAll();
    }

    public Favorites getFavoritesById(FavoritesId id) {
        return favoritesRepository.findById(id).orElse(null);
    }

    public Favorites saveFavorites(Favorites favorites) {
        return favoritesRepository.save(favorites);
    }

    public void deleteFavorites(FavoritesId id) {
        favoritesRepository.deleteById(id);
    }
}


