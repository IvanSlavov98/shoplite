package org.shoplite.productservice.product;

import jakarta.validation.Valid;
import org.shoplite.productservice.product.entity.CreateProductRequest;
import org.shoplite.productservice.product.entity.Product;
import org.shoplite.productservice.product.entity.ProductResponse;
import org.shoplite.productservice.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public List<ProductResponse> list() {
        return this.productService.listProducts();
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody @Valid CreateProductRequest productRequest) {
       return this.productService.createProduct(productRequest);
    }

    @PutMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }

    @GetMapping("/get/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }
}
