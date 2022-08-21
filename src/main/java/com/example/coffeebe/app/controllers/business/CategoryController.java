package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.CategoryFilterDto;
import com.example.coffeebe.app.dtos.responses.CategoryResponse;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.services.impl.business.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController<Category, Long, CategoryResponse, CategoryFilterDto> {

	@Autowired
	CategoryService categoryService;

    public CategoryController() {
        super(CategoryResponse.class, CategoryFilterDto.class);
    }

//    @GetMapping("/tree")
//    public List<CategoryResponse> initRole(){
//        return categoryService.getCategoryTree();
//    }

}
