package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.domain.services.impl.business.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
    PaymentService paymentService;

    @PostMapping(value = "/checkout")
    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
        return paymentService.createPayment(sum);
    }
//
//    @PostMapping(value = "/complete")
//    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
//        return paymentService.completePayment(request);
//    }

}
