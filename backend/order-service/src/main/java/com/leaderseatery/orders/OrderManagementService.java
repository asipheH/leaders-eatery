package com.leaderseatery.orders;

import com.leaderseatery.orders.dto.CatalogDishResponse;
import com.leaderseatery.orders.dto.CreateOrderRequest;
import com.leaderseatery.orders.dto.OrderItemRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderManagementService{
    
    private final OrderRepository orderRepository;
    private final RestClient catalogRestClient;

    public OrderManagementService(OrderRepository orderRepository, RestClient catalogRestClient){
        this.orderRepository = orderRepository;
        this.catalogRestClient = catalogRestClient;
    }

    @Transactional
    public Order createOrder(CreateOrderRequest request){

        Set<Long> dishIds = new HashSet<>();

        for(OrderItemRequest item : request.items()){

            if((!dishIds.add(item.dishId()))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A dish must not appear more than once in an order");
            }
        }

        Order order = new Order();

        order.setCustomerId(request.customerId());
        order.setDeliveryAddress(request.deliveryAddress());
        order.setOrderStatus(OrderStatus.PLACED);

        BigDecimal total = BigDecimal.ZERO;

        for(OrderItemRequest itemRequest : request.items()){

            CatalogDishResponse dish = getDish(itemRequest.dishId());

            if(dish == null || !Boolean.TRUE.equals(dish.available())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dish is unavailable" + itemRequest.dishId());
            }

            if(dish.price() == null || dish.price().signum() < 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid dish price received from Catalog Service");
            }

            BigDecimal lineTotal = dish.price()
                        .multiply(BigDecimal.valueOf(itemRequest.quantity()));

            total = total.add(lineTotal);

            OrderItem orderItem = new OrderItem();

            orderItem.setDishId(dish.dishId());
            orderItem.setDishName(dish.name());
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setUnitPrice(dish.price().setScale(2, RoundingMode.HALF_UP));

            order.addItem(orderItem);
        }

        order.setTotalAmount(total.setScale(2, RoundingMode.HALF_UP));

        return orderRepository.save(order);
    }

    private CatalogDishResponse getDish(Long dishId){

        try{

            return catalogRestClient.get()
                    .uri("/api/dishes/{id}", dishId)
                    .retrieve()
                    .body(CatalogDishResponse.class);
        }
        catch(RestClientException exception){
             throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Could not verify dish with Service: " + dishId, exception);
        }
    }

    @Transactional(readOnly = true)
    public Order getOrder(Long orderId){

        return orderRepository.findById(orderId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @Transactional(readOnly = true)
    public java.util.List<Order> getCustomerOrders(Long customerId){

        return orderRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }
}