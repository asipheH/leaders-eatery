package com.leaderseatery.orders.dto;

import java.math.BigDecimal;
import com.leaderseatery.orders.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(

    Long orderId,
    Long customerId,
    String deliveryAddress,
    BigDecimal totalAmount,
    OrderStatus orderStatus,
    LocalDateTime createdAt,
    List<OrderItemResponse> items
){}