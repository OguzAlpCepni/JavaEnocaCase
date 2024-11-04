package com.enoca.enoca.service.abtracts;

import com.enoca.enoca.service.DTO.Customer.CreateCustomerRequest;
import com.enoca.enoca.service.DTO.Product.CreateProductRequest;

public interface CustomerService {

    void addCustomer(CreateCustomerRequest createCustomerRequest);
}
