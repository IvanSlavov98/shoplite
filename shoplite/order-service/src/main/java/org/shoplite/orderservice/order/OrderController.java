package org.shoplite.orderservice.order;

import org.shoplite.orderservice.order.entity.OrderStatus;
import org.shoplite.orderservice.order.entity.dto.CreateOrderRequest;
import org.shoplite.orderservice.order.entity.dto.OrderMapper;
import org.shoplite.orderservice.order.entity.dto.OrderResponse;
import org.shoplite.orderservice.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public OrderResponse create(@RequestBody CreateOrderRequest request){
        return OrderMapper.toResponse(orderService.create(request));
    }

    @GetMapping("/get/{id}")
    public OrderResponse getOrderById(@PathVariable Long id){
        return OrderMapper.toResponse(orderService.getOrderById(id));
    }

    @PutMapping("update-status/{id}")
    public OrderResponse updateStatus(@PathVariable Long id, @RequestParam OrderStatus status){
        return OrderMapper.toResponse(orderService.updateStatus(id, status));
    }
}
