package org.shoplite.productservice.product.entity;

import lombok.Builder;
import lombok.Data;
import org.shoplite.productservice.category.entity.CategoryType;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Integer stock;
    private BigDecimal price;
    private CategoryType category;

    public static ProductResponse from(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .stock(p.getStock())
                .price(p.getPrice())
                .category(p.getCategory().getType())
                .build();
    }
}
