package org.shoplite.productservice.product.service;

import org.shoplite.productservice.product.entity.CreateProductRequest;
import org.shoplite.productservice.product.entity.Product;
import org.shoplite.productservice.product.entity.ProductResponse;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> listProducts();
    public Product createProduct(CreateProductRequest productRequest);
    public void deleteProduct(Long id);
    ProductResponse getProduct(Long id);
}
