package com.example.coffeebe.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SliderResponse {
    private Long id;

    private String name;

    private String link;

    private Boolean status;
}
