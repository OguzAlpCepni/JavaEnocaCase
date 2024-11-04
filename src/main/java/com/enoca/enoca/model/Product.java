package com.enoca.enoca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity{

    @Column(name = "product_name")
    private String productName;
    @Column(name = "price", precision = 19, scale = 4)
    private BigDecimal price;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_in_stock")
    private int unitInStock;
    @Column(name = "sku")
    private String sku;

    // relation with cartItem
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade =  CascadeType.ALL)
    private List<CardItem> cartItems = new ArrayList<>();


}
