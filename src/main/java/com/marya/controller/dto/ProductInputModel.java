package com.marya.controller.dto;

import lombok.Data;

@Data
public class ProductInputModel {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private double price;
    private int quantity;

    public void clear(){
        this.id = null;
        this.name = null;
        this.description = null;
        this.categoryId = null;
        this.price = 0.0;
        this.quantity = 0;
    }

}
