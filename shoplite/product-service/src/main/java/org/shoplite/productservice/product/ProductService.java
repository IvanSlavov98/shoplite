package org.shoplite.productservice.product;

import org.shoplite.productservice.product.entity.CreateProductRequest;
import org.shoplite.productservice.product.entity.Product;
import org.shoplite.productservice.product.entity.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> listProducts();
    public Product createProduct(CreateProductRequest productRequest);
    public void deleteProduct(Long id);
}
