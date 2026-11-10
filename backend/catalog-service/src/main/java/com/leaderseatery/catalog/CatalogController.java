package com.leaderseatery.catalog;

import com.leaderseatery.catalog.dto.CategoryResponse;
import com.leaderseatery.catalog.dto.DishResponse;
import com.leaderseatery.catalog.dto.RestaurantResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogController{

    private final CatalogQueryService catalogQueryService;

    public CatalogController(CatalogQueryService catalogQueryService){
        this.catalogQueryService = catalogQueryService;
    }

    @GetMapping("/restaurants")
    public List<RestaurantResponse> getRestaurants(){
        return catalogQueryService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public RestaurantResponse getRestaurant(@PathVariable Long id){
        return catalogQueryService.getRestaurant(id);
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories(){
        return catalogQueryService.getCategories();
    }

    @GetMapping("/dishes")
    public List<DishResponse> getDishes(
        @RequestParam(required = false) Long restaurantId,
        @RequestParam(required = false) Long categoryId,
        @RequestParam(required = false) String name){

            return catalogQueryService.getDishes(restaurantId, categoryId, name);
    }

    @GetMapping("/dishes/{id}")
    public DishResponse getDish(@PathVariable Long id){
        return catalogQueryService.getDish(id);
    }
}