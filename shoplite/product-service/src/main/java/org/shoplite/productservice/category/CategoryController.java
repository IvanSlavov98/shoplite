package org.shoplite.productservice.category;

import org.shoplite.productservice.category.entity.Category;
import org.shoplite.productservice.category.entity.CategoryRepository;
import org.shoplite.productservice.category.entity.CreateCategoryRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<Category> list() {
        return this.categoryService.getAllCategories();
    }

    @PostMapping("/create")
    public Category create(@RequestBody CreateCategoryRequest category) {
        return this.categoryService.createCategory(category);
    }
}
