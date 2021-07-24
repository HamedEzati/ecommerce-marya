package com.marya.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "products")
@Setter
@Getter
public class Product extends BaseEntity {

    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private double price;
    private int quantity;

}
