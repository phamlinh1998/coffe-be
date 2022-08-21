package com.example.coffeebe.app.dtos.responses;

import com.example.coffeebe.domain.entities.business.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String detail;

    private Double price;

    private String image;

    private CategoryResponse category;

    private List<Discount> discounts;

    private String imageList;

    private Integer quantity;

    private String description;
}
