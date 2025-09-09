package org.shoplite.productservice.category;

import org.shoplite.productservice.category.entity.Category;
import org.shoplite.productservice.category.entity.CategoryRepository;
import org.shoplite.productservice.category.entity.CreateCategoryRequest;
import org.shoplite.productservice.product.entity.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.shoplite.productservice.common.db.DbErrors;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Cacheable(cacheNames = "category:list", key = "'all'")
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAllActive();
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "category:list", key = "'all'")
    })
    public Category createCategory(CreateCategoryRequest categoryRequest) {

        Category category = Category.builder()
                .name(categoryRequest.getName())
                .type(categoryRequest.getType())
                .build();

        try {
            return categoryRepository.saveAndFlush(category);
        } catch (DataIntegrityViolationException ex) {
            if (DbErrors.isUniqueViolation(ex)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Category type '%s' already exists".formatted(categoryRequest.getType())
                );
            }
            throw ex;
        }
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "category:list", key = "'all'")
    })
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        category.setDelFlag(true);
        categoryRepository.save(category);
    }
}
