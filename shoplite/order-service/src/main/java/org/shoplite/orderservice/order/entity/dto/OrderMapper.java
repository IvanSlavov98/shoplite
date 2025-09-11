package org.shoplite.orderservice.order.entity.dto;

import org.shoplite.orderservice.order.entity.Order;
import org.shoplite.orderservice.order.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class OrderMapper {
    private OrderMapper() {
    }

    public static Order toEntity(CreateOrderRequest request) {
        Order order = Order
                .builder()
                .customerEmail(request.customerEmail())
                .totalAmount(BigDecimal.ZERO)
                .build();

        request.items().forEach(i -> {
            OrderItem orderItem = OrderItem
                    .builder()
                    .productId(i.productId())
                    .quantity(i.quantity())
                    .unitPrice(i.unitPrice())
                    .build();
            order.addItem(orderItem);
            order.setTotalAmount(order.getTotalAmount().add(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(i.quantity()))));
        });

        return order;
    }

    public static OrderResponse toResponse(Order order) {
        List<OrderResponse.Item> items = order.getItems()
                .stream()
                .map(item -> new OrderResponse.Item(item.getProductId(), item.getQuantity(), item.getUnitPrice()))
                .collect(Collectors.toList());
        return new OrderResponse(order.getId(), order.getCustomerEmail(), order.getOrderStatus(), order.getTotalAmount(), order.getCreatedAt(), items);
    }
}
