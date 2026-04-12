package org.gsk.product.service;

import jakarta.validation.Valid;
import org.gsk.product.model.product.ProductRequest;
import org.gsk.product.model.product.ProductResponse;
import org.gsk.product.model.purchase.ProductPurchaseRequest;
import org.gsk.product.model.purchase.ProductPurchaseResponse;

import java.util.List;

public interface ProductService {
    String createProduct(@Valid ProductRequest request);

    ProductResponse findById(Integer id);

    List<ProductResponse> findAll();

    List<ProductPurchaseResponse> purchaseProduct(@Valid List<ProductPurchaseRequest> purchaseRequests);
}
