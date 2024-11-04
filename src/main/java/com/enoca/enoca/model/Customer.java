package com.enoca.enoca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;

    //Relation with card
    @OneToOne(mappedBy = "customer", cascade =  CascadeType.ALL)
    @JsonIgnore
    private Card card;

    //Relation with order
    @OneToMany(mappedBy = "customer", cascade =  CascadeType.ALL) // I Controlled remove issue    private List<Order> orders= new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
}
