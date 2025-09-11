package org.shoplite.orderservice.order.service;

import org.shoplite.orderservice.order.entity.Order;
import org.shoplite.orderservice.order.entity.OrderStatus;
import org.shoplite.orderservice.order.entity.dto.CreateOrderRequest;

public interface OrderService {
    Order create(CreateOrderRequest request);
    Order getOrderById(Long id);
    Order updateStatus(Long id, OrderStatus status);
}
