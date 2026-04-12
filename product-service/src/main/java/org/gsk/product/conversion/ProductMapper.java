package org.gsk.product.conversion;

import org.gsk.product.entity.Category;
import org.gsk.product.entity.Product;
import org.gsk.product.model.product.ProductRequest;
import org.gsk.product.model.product.ProductResponse;
import org.gsk.product.model.purchase.ProductPurchaseResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapper {

    public Product dtoToEntity(ProductRequest request, Category category) {
        return Product.builder()
                .price(request.price())
                .category(category)
                .productName(request.productName())
                .quantity(request.quantity())
                .build();
    }

    public ProductResponse entityToDto(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .categoryDescription(product.getCategory().getDescription())
                .categoryName(product.getCategory().getName())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product fromDbProduct, Integer quantity) {
        return ProductPurchaseResponse.builder()
                .id(fromDbProduct.getId())
                .price(fromDbProduct.getPrice())
                .productName(fromDbProduct.getProductName())
                .quantity(quantity)
                .build();
    }
}
