package com.leaderseatery.orders;

import com.leaderseatery.orders.dto.OrderItemResponse;
import com.leaderseatery.orders.dto.OrderResponse;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderResponseMapper{

    public OrderResponse toResponse(Order order){

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(item -> new OrderItemResponse(
                    item.getOrderItemId(),
                    item.getDishId(),
                    item.getDishName(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getUnitPrice().multiply(java.math.BigDecimal.valueOf(item.getQuantity()))
                ))
                .toList();

                return new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerId(),
                    order.getDeliveryAddress(),
                    order.getTotalAmount(),
                    order.getOrderStatus(),
                    order.getCreatedAt(),
                    items
                );
    }
}