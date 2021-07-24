package com.marya.service.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class PageQueryParams implements Cloneable{
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
    private Sort sort;
    private Set<String> sortKey;
}