package com.enoca.enoca.service.abtracts;

import com.enoca.enoca.service.DTO.Product.CreateProductRequest;
import com.enoca.enoca.service.DTO.Product.GetAllProductResponse;
import com.enoca.enoca.service.DTO.Product.GetByIdProductResponse;
import com.enoca.enoca.service.DTO.Product.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    List<GetAllProductResponse> getAllProducts();
    GetByIdProductResponse getProduct(String id);
    void createProduct(CreateProductRequest createProductRequest);
    void UpdateProduct(UpdateProductRequest updateProductRequest,String id);
    void deleteProduct(String id);
}
