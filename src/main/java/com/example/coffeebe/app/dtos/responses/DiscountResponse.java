package com.example.coffeebe.app.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {

    private Long id;

    private ProductResponse product;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date endDate;

    private String name;

    private Integer discount;
}
