package com.leaderseatery.catalog.dto;

public record RestaurantResponse(
    Long restaurantId,
    String name,
    String description,
    String cuisine,
    String address,
    String imageUrl,
    Boolean active
){}