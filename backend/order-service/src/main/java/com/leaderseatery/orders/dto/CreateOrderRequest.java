package com.leaderseatery.orders.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateOrderRequest(

    @NotNull
    Long customerId,

    @NotBlank
    @Size(max = 255)
    String deliveryAddress,

    @NotNull
    @Size(min = 1)
    List<@Valid OrderItemRequest> items
){}