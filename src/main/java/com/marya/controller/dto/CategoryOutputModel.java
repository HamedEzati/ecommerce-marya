package com.marya.controller.dto;

import com.marya.entity.Category;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CategoryOutputModel {

    private Long id;
    private String name;
    private List<Long> subcategories;

}
