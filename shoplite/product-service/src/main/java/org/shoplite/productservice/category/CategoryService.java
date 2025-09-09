package org.shoplite.productservice.category;

import org.shoplite.productservice.category.entity.Category;
import org.shoplite.productservice.category.entity.CreateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    public Category createCategory(CreateCategoryRequest categoryRequest);
    public List<Category> getAllCategories();
    public void deleteCategory(Long id);
}
