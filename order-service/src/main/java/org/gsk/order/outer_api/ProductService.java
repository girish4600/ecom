package org.gsk.order.outer_api;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.gsk.order.handler.BusinessException;
import org.gsk.order.model.product.ProductPurchaseRequest;
import org.gsk.order.model.product.ProductPurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
//@FeignClient(name = "product-service", url = "http://localhost:9092")
@Slf4j
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.config.product-url:http://localhost:9090}")
    private String productUrl;


    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseRequest) {
        log.info("ProductService purchaseProduct");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<ProductPurchaseRequest>>   requestEntity = new HttpEntity<>(purchaseRequest, httpHeaders);
        System.out.println("productUrl :: "+ productUrl);
        ParameterizedTypeReference<List<ProductPurchaseResponse>> responseType = new ParameterizedTypeReference<List<ProductPurchaseResponse>>() {};
        ResponseEntity<List<ProductPurchaseResponse>> responseEntity =  restTemplate.exchange(productUrl+"/v1/product/purchase",
                        HttpMethod.POST,
                        requestEntity,
                        responseType
                );
        log.info("responseEntity :: {}", responseEntity.getBody());
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("Error occurred while processing products" + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }

}
