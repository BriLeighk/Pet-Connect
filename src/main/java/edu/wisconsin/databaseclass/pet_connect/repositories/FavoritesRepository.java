package edu.wisconsin.databaseclass.pet_connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.wisconsin.databaseclass.pet_connect.entities.Favorites;
import edu.wisconsin.databaseclass.pet_connect.entities.FavoritesId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, FavoritesId> {
    @Query("SELECT f FROM Favorites f WHERE f.user.user_ID = :userId")
    List<Favorites> findByUserId(@Param("userId") int userId);
}