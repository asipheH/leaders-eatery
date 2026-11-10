package com.leaderseatery.catalog;

import com.leaderseatery.catalog.dto.CategoryResponse;
import com.leaderseatery.catalog.dto.DishResponse;
import com.leaderseatery.catalog.dto.RestaurantResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CatalogQueryService{

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;

    public CatalogQueryService(RestaurantRepository restaurantRepository, CategoryRepository categoryRepository, DishRepository dishRepository ){
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
        this.dishRepository = dishRepository;
    }

    public List<RestaurantResponse> getRestaurants(){

        return restaurantRepository.findByActiveTrue().stream()
                                                      .map(r -> new RestaurantResponse(
                                                        r.getRestaurantId(),
                                                        r.getName(),
                                                        r.getDescription(),
                                                        r.getCuisine(),
                                                        r.getAddress(),
                                                        r.getImageUrl(),
                                                        r.getActive()))
                                                        .toList();
    }

    public RestaurantResponse getRestaurant(Long id){

        Restaurant r = restaurantRepository.findById(id)
            .filter(Restaurant::getActive)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

            return new RestaurantResponse(
                r.getRestaurantId(),
                r.getName(),
                r.getDescription(),
                r.getCuisine(),
                r.getAddress(),
                r.getImageUrl(),
                r.getActive()
            );
    }

    public List<CategoryResponse> getCategories(){

        return categoryRepository.findAll()
            .stream()
            .map(c -> new CategoryResponse(
                c.getCategoryId(),
                c.getCategoryName()
            ))
            .toList();
    }

    public List<DishResponse> getDishes(
        Long restaurantId,
        Long categoryId,
        String name) {

    List<Dish> dishes;

    if (restaurantId != null) {
        dishes =
            dishRepository.findByRestaurantRestaurantIdAndAvailableTrue(
                restaurantId
            );
    } else if (categoryId != null) {
        dishes =
            dishRepository.findByCategoryCategoryIdAndAvailableTrue(
                categoryId
            );
    } else if (name != null && !name.isBlank()) {
        dishes =
            dishRepository.findByNameContainingIgnoreCaseAndAvailableTrue(
                name
            );
    } else {
        dishes = dishRepository.findByAvailableTrue();
    }

    return dishes.stream()
        .map(this::toDishResponse)
        .toList();
}

    public DishResponse getDish(Long id){

        Dish dish = dishRepository.findById(id)
                    .filter(Dish::getAvailable)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found"));
        return toDishResponse(dish);
    }

    private DishResponse toDishResponse(Dish dish) {

    Long categoryId = dish.getCategory() == null
        ? null
        : dish.getCategory().getCategoryId();

    String categoryName = dish.getCategory() == null
        ? null
        : dish.getCategory().getCategoryName();

    return new DishResponse(
        dish.getDishId(),
        dish.getRestaurant().getName(),
        categoryId,
        categoryName,
        dish.getName(),
        dish.getDescription(),
        dish.getPrice(),
        dish.getImageUrl(),
        dish.getAvailable()
    );
}
}