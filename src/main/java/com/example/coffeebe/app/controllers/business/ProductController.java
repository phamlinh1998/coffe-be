package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.ProductFilterDto;
import com.example.coffeebe.app.dtos.responses.ProductResponse;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.services.impl.business.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController<Product, Long, ProductResponse, ProductFilterDto> {

	@Autowired
    ProductService productService;

    public ProductController() {
        super(ProductResponse.class, ProductFilterDto.class);
    }

}
