package com.leaderseatery.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    List<Restaurant> findByActiveTrue();

    List<Restaurant> findByNameContainingIgnoreCase(String name);

    List<Restaurant> findByCuisineIgnoreCase(String cuisine);
}