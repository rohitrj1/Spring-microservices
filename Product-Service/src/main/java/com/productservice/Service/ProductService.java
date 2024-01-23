package com.productservice.Service;

import com.productservice.Dto.ProductRequest;
import com.productservice.Dto.ProductResponse;
import com.productservice.Repository.ProductRepo;
import com.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
//        product.setId(UUID.randomUUID().toString());
        this.productRepo.save(product);
        log.info("Product {} is saved" ,product.getId());
    }

    public List<ProductResponse> getAllProdcut(){
        List<Product> product = productRepo.findAll();
      return  product.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
