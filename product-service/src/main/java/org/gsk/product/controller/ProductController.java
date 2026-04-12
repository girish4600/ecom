package org.gsk.product.controller;

import jakarta.validation.Valid;
import org.gsk.product.model.product.ProductRequest;
import org.gsk.product.model.product.ProductResponse;
import org.gsk.product.model.purchase.ProductPurchaseRequest;
import org.gsk.product.model.purchase.ProductPurchaseResponse;
import org.gsk.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productServiceImpl;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest request) {
        return new ResponseEntity<>(productServiceImpl.createProduct(request), HttpStatus.CREATED);
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ProductResponse> findById(@PathVariable(name = "pid") Integer id) {
        return new ResponseEntity<>(productServiceImpl.findById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return new ResponseEntity<>(productServiceImpl.findAll(), HttpStatus.FOUND);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> purchaseRequest) {
        return ResponseEntity.ok(productServiceImpl.purchaseProduct(purchaseRequest));
    }
}
