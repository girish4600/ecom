package org.gsk.product.service;

import lombok.extern.slf4j.Slf4j;
import org.gsk.product.conversion.ProductMapper;
import org.gsk.product.model.ProductRequest;
import org.gsk.product.model.ProductResponse;
import org.gsk.product.repository.CategoryRepository;
import org.gsk.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ProductMapper mapper;

    @Override
    public String createProduct(ProductRequest request) {
        log.info("fetching category for :: "+ request.categoryId());
        var category = categoryRepository.findById(request.categoryId()).orElseThrow(()->new RuntimeException("Category Not found :: "+ request.categoryId()));
        return "Product record saved with ID :: " +   productRepository.save(mapper.dtoToEntity(request, category)).getId();
    }

    @Override
    public ProductResponse findById(Integer id) {
        var product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found for ID :: "+id));

        return mapper.entityToDto(product);
    }

    @Override
    public List<ProductResponse> fimdAll() {
        return productRepository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}
