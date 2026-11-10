package com.leaderseatery.orders.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(

    @NotNull
    Long dishId,

    @NotNull
    @Min(1)
    Integer quantity
){}