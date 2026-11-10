package com.leaderseatery.orders;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_items")
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "dish_id", nullable = false)
    private Long dishId;

    @Column(name = "dish_name", nullable = false)
    private String dishName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    public OrderItem(){}

    public Long getOrderItemId(){
        return orderItemId;
    }

    public Order getOrder(){
        return order;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public Long getDishId(){
        return dishId;
    }

    public void setDishId(Long dishId){
        this.dishId = dishId;
    }

    public String getDishName(){
        return dishName;
    }

    public void setDishName(String dishName){
        this.dishName = dishName;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice(){
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice){
        this.unitPrice = unitPrice;
    }
}

