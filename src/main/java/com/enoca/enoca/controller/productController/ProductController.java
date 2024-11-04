package com.enoca.enoca.controller.productController;

import com.enoca.enoca.model.Product;
import com.enoca.enoca.service.DTO.Product.CreateProductRequest;
import com.enoca.enoca.service.DTO.Product.GetAllProductResponse;
import com.enoca.enoca.service.DTO.Product.GetByIdProductResponse;
import com.enoca.enoca.service.DTO.Product.UpdateProductRequest;
import com.enoca.enoca.service.abtracts.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetByIdProductResponse> getProductBySku(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createProduct(@RequestBody CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
        return ResponseEntity.ok("Product created");
    }
    @PutMapping("/id/{id}")
    public ResponseEntity UpdateProduct(@RequestBody UpdateProductRequest updateProductRequest,@PathVariable("id") String id) {
        productService.UpdateProduct(updateProductRequest,id);
        return ResponseEntity.ok("Product updated");
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }
}
