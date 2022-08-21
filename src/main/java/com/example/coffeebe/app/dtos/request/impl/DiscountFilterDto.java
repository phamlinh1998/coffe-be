package com.example.coffeebe.app.dtos.request.impl;


import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.domain.entities.business.Discount;
import lombok.Data;

@Data
public class DiscountFilterDto implements FilterDto<Discount> {
    private Long productId;
}
