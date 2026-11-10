package com.leaderseatery.orders.dto;

import java.math.BigDecimal;

public record CatalogDishResponse(

    Long dishId,
    Long restaurantId,
    String restaurantName,
    Long categoryId,
    String categoryName,
    String name,
    String description,
    BigDecimal price,
    String imageUrl,
    Boolean available
){}