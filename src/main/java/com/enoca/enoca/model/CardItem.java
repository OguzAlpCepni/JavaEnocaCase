package com.enoca.enoca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "card_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardItem extends BaseEntity{

    //Relation with card
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    // Relation with product
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name ="quantity")
    private int quantity;


    @Column(name = "total_price")
    private BigDecimal totalPrice;

}
