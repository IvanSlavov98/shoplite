package org.shoplite.orderservice.order.entity.dto;

import org.shoplite.orderservice.order.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String customerEmail,
        OrderStatus orderStatus,
        BigDecimal totalAmount,
        OffsetDateTime createdAt,
        List<Item> items
) {
    public record Item(Long productId, Integer quantity, BigDecimal unitPrice) {
    }
}
