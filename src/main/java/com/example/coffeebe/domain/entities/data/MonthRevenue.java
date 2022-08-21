package com.example.coffeebe.domain.entities.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthRevenue {
    private int month;
    private Double amount;
}
