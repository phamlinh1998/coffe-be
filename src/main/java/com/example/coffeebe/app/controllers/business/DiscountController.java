package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.DiscountFilterDto;
import com.example.coffeebe.app.dtos.responses.DiscountResponse;
import com.example.coffeebe.domain.entities.business.Discount;
import com.example.coffeebe.domain.services.impl.business.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountController extends BaseController<Discount, Long, DiscountResponse, DiscountFilterDto> {

    @Autowired
    DiscountService discountService;

    protected DiscountController() {
        super(DiscountResponse.class, DiscountFilterDto.class);
    }

}
