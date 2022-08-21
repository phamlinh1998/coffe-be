package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.ProductRating;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonTypeName("rating")
public class ProductRatingDto implements DTO<ProductRating> {

    @NotNull(message = "product_id  not null")
    @JsonProperty("product_id")
    private Long productId;

    @NotNull(message = "content not noll")
    private String content;

    @NotNull(message = "number_start not null")
    @JsonProperty("number_star")
    private Integer numberStar;

}
