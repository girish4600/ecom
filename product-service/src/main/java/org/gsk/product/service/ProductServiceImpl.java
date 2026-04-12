package org.gsk.product.service;

import lombok.extern.slf4j.Slf4j;
import org.gsk.product.conversion.ProductMapper;
import org.gsk.product.handler.ProductPurchaseException;
import org.gsk.product.model.product.ProductRequest;
import org.gsk.product.model.product.ProductResponse;
import org.gsk.product.model.purchase.ProductPurchaseRequest;
import org.gsk.product.model.purchase.ProductPurchaseResponse;
import org.gsk.product.repository.CategoryRepository;
import org.gsk.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ProductMapper mapper;

    @Override
    public String createProduct(ProductRequest request) {
        log.info("fetching category for :: " + request.categoryId());
        var category = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new RuntimeException("Category Not found :: " + request.categoryId()));
        return "Product record saved with ID :: " + productRepository.save(mapper.dtoToEntity(request, category)).getId();
    }

    @Override
    public ProductResponse findById(Integer id) {
        return productRepository.findById(id).map(mapper::entityToDto).orElseThrow(() -> new RuntimeException("Product not found for ID :: " + id));
    }

    @Override
    public List<ProductResponse> findAll() {
        log.info("findAll products");
        return productRepository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseRequests) {
        var productIds = purchaseRequests.stream().map(ProductPurchaseRequest::productId).collect(Collectors.toSet()).stream().toList();
        System.out.println("productIds :: "+ productIds);
        var storedIds = productRepository.findAllByIdInOrderById(productIds);
        System.out.println("storedIds :: "+ storedIds);
        if (productIds.size() != storedIds.size()) {
            throw new ProductPurchaseException("ONE OR MORE products are not available");
        }
        var purchaseList = purchaseRequests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < purchaseList.size(); i++) {
            var productRequest = purchaseList.get(i);
            var fromDbProduct = storedIds.get(i);
            System.out.println("productRequest.quantity() :: "+ productRequest.quantity());
            System.out.println("fromDbProduct.getQuantity() :: "+ fromDbProduct.getQuantity());
            if (productRequest.quantity() > fromDbProduct.getQuantity()) {
                throw new ProductPurchaseException("Quantity is not available for the item :: " + fromDbProduct.getProductName());
            }
            var remainingQuantity = fromDbProduct.getQuantity() - productRequest.quantity();
            fromDbProduct.setQuantity(remainingQuantity);
            productRepository.save(fromDbProduct);
            purchasedProducts.add(mapper.toProductPurchaseResponse(fromDbProduct, productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
