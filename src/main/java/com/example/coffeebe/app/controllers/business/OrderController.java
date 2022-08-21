package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.OrderDto;
import com.example.coffeebe.app.dtos.request.impl.OrderFilterDto;
import com.example.coffeebe.app.dtos.responses.OrderResponse;
import com.example.coffeebe.domain.entities.business.Order;
import com.example.coffeebe.domain.services.impl.business.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order1")
public class OrderController extends BaseController<Order, Long, OrderResponse, OrderFilterDto> {

    @Autowired
    OrderService orderService;

    public OrderController(){
        super(OrderResponse.class, OrderFilterDto.class);
    }
}
