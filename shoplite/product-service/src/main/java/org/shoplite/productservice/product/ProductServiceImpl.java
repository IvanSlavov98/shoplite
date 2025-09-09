package org.shoplite.productservice.product;

import org.shoplite.productservice.category.entity.Category;
import org.shoplite.productservice.category.entity.CategoryRepository;
import org.shoplite.productservice.product.entity.CreateProductRequest;
import org.shoplite.productservice.product.entity.Product;
import org.shoplite.productservice.product.entity.ProductRepository;
import org.shoplite.productservice.product.entity.ProductResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Cacheable(cacheNames = "product:list", key="'all'")
    @Transactional(readOnly = true)
    public List<ProductResponse> listProducts() {
        return productRepository.findAllActive()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "product:list", key = "'all'")
    })
    @Transactional
    public Product createProduct(CreateProductRequest productRequest) {
        Category category = categoryRepository.findByType(productRequest.getCategory())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Category '%s' not found".formatted(productRequest.getCategory())));

        Product product = Product.builder()
                .name(productRequest.getName())
                .stock(productRequest.getStock())
                .price(productRequest.getPrice())
                .category(category)
                .build();

        return productRepository.save(product);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "product:list", key = "'all'")
    })
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.setDelFlag(true);
        productRepository.save(product);
    }

}
