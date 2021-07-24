package com.marya.controller.dto;

import lombok.Data;

@Data
public class CategoryInputModel {

    private String name;
    private Long parentId;

}
