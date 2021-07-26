package com.marya.controller.dto;

import lombok.Data;

@Data
public class CategoryInputModel {

    private Long id;
    private String name;
    private Long parentId;

}
