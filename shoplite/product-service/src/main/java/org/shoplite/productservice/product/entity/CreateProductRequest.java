package org.shoplite.productservice.product.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.shoplite.productservice.category.entity.CategoryType;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @NotNull
    private CategoryType category;
}
