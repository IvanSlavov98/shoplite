package org.shoplite.productservice.category.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotBlank
    private CategoryType type;
    @NotBlank
    private String name;
}
