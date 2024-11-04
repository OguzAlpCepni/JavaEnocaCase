package com.enoca.enoca.service.DTO.Customer;

import com.enoca.enoca.model.Card;
import com.enoca.enoca.model.Order;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {


    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String city;

    private String country;

    private Card card;

    private List<Order> orders = new ArrayList<>();
}
