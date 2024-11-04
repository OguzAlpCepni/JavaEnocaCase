package com.enoca.enoca.service.concreate;

import com.enoca.enoca.core.exceptions.ProductNotFoundException;
import com.enoca.enoca.core.modelMapper.ModelMapperService;
import com.enoca.enoca.model.Product;
import com.enoca.enoca.repository.ProductRepository;
import com.enoca.enoca.service.DTO.Product.CreateProductRequest;
import com.enoca.enoca.service.DTO.Product.GetAllProductResponse;
import com.enoca.enoca.service.DTO.Product.GetByIdProductResponse;
import com.enoca.enoca.service.DTO.Product.UpdateProductRequest;
import com.enoca.enoca.service.HelperMethods.ProductHelper.ProductMappingHelperMethods;
import com.enoca.enoca.service.Rules.ProductRules.ProductBusinessRules;
import com.enoca.enoca.service.abtracts.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMappingHelperMethods productMappingHelperMethods;
    private final ProductBusinessRules productBusinessRules;
    private final ModelMapperService modelMapperService;

    public ProductManager(ProductRepository productRepository, ProductMappingHelperMethods productHelperMethods, ProductBusinessRules productBusinessRules,ModelMapperService modelMapperService) {

        this.productRepository = productRepository;
        this.productMappingHelperMethods = productHelperMethods;
        this.productBusinessRules = productBusinessRules;
        this.modelMapperService = modelMapperService;

    }

    @Override
    public List<GetAllProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMappingHelperMethods.mapToDtoList(products);
    }

    @Override
    public GetByIdProductResponse getProduct(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product could not found by  id: " + id));
        return productMappingHelperMethods.mapToDtoById(product);
    }

    @Override
    public void createProduct(CreateProductRequest createProductRequest) {
        productBusinessRules.checkIfProductSkuExists(createProductRequest.getSku());                // exception check
        Product product =modelMapperService.forRequest().map(createProductRequest,Product.class);   // different way to Dto map
        this.productRepository.save(product);
    }

    @Override
    public void UpdateProduct(UpdateProductRequest updateProductRequest,String id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product could not found by  id: " + id));
        Product changedProduct = this.modelMapperService.forRequest().map(updateProductRequest, Product.class);
        changedProduct.setId(id);
        this.productRepository.save(changedProduct);

    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
