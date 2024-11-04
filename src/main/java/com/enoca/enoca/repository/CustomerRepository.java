package com.enoca.enoca.repository;

import com.enoca.enoca.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {


}
