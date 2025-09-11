package org.shoplite.orderservice.order.entity.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(
        @Email
        @NotBlank
        String customerEmail,
        @NotEmpty
        List<Item> items
) {
        public record Item(
                @NotNull
                Long productId,
                @NotNull
                @Positive
                Integer quantity,
                @NotNull @Positive
                BigDecimal unitPrice
        ){}

}

