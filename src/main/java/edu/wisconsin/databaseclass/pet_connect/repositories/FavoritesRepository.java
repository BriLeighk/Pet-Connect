package edu.wisconsin.databaseclass.pet_connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.wisconsin.databaseclass.pet_connect.entities.Favorites;
import edu.wisconsin.databaseclass.pet_connect.entities.FavoritesId;

import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, FavoritesId> {
}

