package org.shoplite.orderservice.order.service;

import org.shoplite.orderservice.order.entity.Order;
import org.shoplite.orderservice.order.entity.OrderRepository;
import org.shoplite.orderservice.order.entity.OrderStatus;
import org.shoplite.orderservice.order.entity.dto.CreateOrderRequest;
import org.shoplite.orderservice.order.entity.dto.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order create(CreateOrderRequest request) {
        Order order = OrderMapper.toEntity(request);
        order.setOrderStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found"));
    }

    @Override
    @Transactional
    public Order updateStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}
