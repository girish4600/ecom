package org.gsk.product.model.purchase;

import lombok.Builder;

@Builder
public record ProductPurchaseRequest (

        Integer productId,
        Integer quantity

){
}

