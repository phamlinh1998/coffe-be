package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonTypeName("category")
public class CategoryDto implements DTO<Category> {

    private Long id;

    @NotNull(message = "Name not null")
    private String name;

    @NotNull(message = "position not null")
    private Integer position;

    @NotNull(message = "ProductDetail not null")
    @JsonProperty("product_detail")
    private String productDetail;

}
