package com.enoca.enoca.controller.CustomerController;

import com.enoca.enoca.service.DTO.Customer.CreateCustomerRequest;
import com.enoca.enoca.service.abtracts.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        customerService.addCustomer(createCustomerRequest);
        return ResponseEntity.ok("Customer created");
    }
}
