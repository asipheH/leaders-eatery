package com.leaderseatery.orders.dto;

import java.math.BigDecimal;

public record OrderItemResponse(

    Long orderItemId,
    Long dishId,
    String dishName,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal subtotal
){}