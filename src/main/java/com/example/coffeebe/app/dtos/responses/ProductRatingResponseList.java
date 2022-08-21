package com.example.coffeebe.app.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRatingResponseList {
    private String name;
    private String content;
    private Integer number_star;
    private Timestamp time;
}
