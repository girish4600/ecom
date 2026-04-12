package org.gsk.product.service;

import jakarta.validation.Valid;
import org.gsk.product.model.ProductRequest;
import org.gsk.product.model.ProductResponse;

import java.util.List;

public interface ProductService {
    String createProduct(@Valid ProductRequest request);

    ProductResponse findById(Integer id);

    List<ProductResponse> fimdAll();
}
