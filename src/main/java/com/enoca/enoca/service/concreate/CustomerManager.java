package com.enoca.enoca.service.concreate;

import com.enoca.enoca.core.modelMapper.ModelMapperService;
import com.enoca.enoca.model.Card;
import com.enoca.enoca.model.Customer;
import com.enoca.enoca.repository.CardRepository;
import com.enoca.enoca.repository.CustomerRepository;
import com.enoca.enoca.service.DTO.Customer.CreateCustomerRequest;
import com.enoca.enoca.service.abtracts.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements CustomerService {

    private final CustomerRepository CustomerRepository;
    private final ModelMapperService modelMapperService;
    private final CardRepository cardRepository;

    public CustomerManager(CustomerRepository CustomerRepository, ModelMapperService modelMapperService, CardRepository cardRepository) {
        this.CustomerRepository = CustomerRepository;
        this.modelMapperService = modelMapperService;
        this.cardRepository = cardRepository;
    }

    @Override
    public void addCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer =modelMapperService.forRequest().map(createCustomerRequest,Customer.class);
        Card card = new Card();
        card.setCustomer(customer);
        customer.setCard(card);

        this.CustomerRepository.save(customer);
        this.cardRepository.save(card);

    }
}
