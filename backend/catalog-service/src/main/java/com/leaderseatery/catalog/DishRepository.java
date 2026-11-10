package com.leaderseatery.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long>{

    List<Dish> findByAvailableTrue();

    List<Dish> findByCategoryCategoryIdAndAvailableTrue(Long categoryId);

    List<Dish> findByRestaurantRestaurantIdAndAvailableTrue(Long restaurantId);

    List<Dish> findByNameContainingIgnoreCaseAndAvailableTrue(String name);
}