package com.enoca.enoca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity{

    //Relation with customer
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private Customer customer;

    // Relation with cartItem
    @OneToMany(mappedBy = "card", cascade =  CascadeType.ALL,orphanRemoval = true) // I Controlled remove issue
    private List<CardItem> cartItems = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice = BigDecimal.ZERO;


}
