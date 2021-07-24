package com.marya.controller.dto;

import lombok.Data;

@Data
public class ProductInputModel {

    private String name;
    private String description;
    private Long categoryId;
    private double price;
    private int quantity;

}
