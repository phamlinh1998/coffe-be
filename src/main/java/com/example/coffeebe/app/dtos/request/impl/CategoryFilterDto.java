package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.domain.entities.business.Category;
import lombok.Data;

@Data
public class CategoryFilterDto implements FilterDto<Category> {

}
