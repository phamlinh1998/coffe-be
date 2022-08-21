package com.example.coffeebe.app.dtos.request.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@JsonTypeName("order")
public class OrderDto {

    @NotNull(message = "quantity not null")
    @Min(value = 1, message = "quantity larger than 0")
    private Integer quantity;

    @NotNull(message = "product_id not null")
    @JsonProperty("product_id")
    private Long productID;

    @JsonProperty("discount_id")
    private Long discountId;

}
