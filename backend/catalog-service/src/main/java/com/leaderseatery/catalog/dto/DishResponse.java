package com.leaderseatery.catalog.dto;

import java.math.BigDecimal;

public record DishResponse(
    Long dishId,
    String restaurantName,
    Long categoryId,
    String categoryName,
    String name,
    String description,
    BigDecimal price,
    String imageUrl,
    Boolean available
){}