package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.TransactionFilterDto;
import com.example.coffeebe.app.dtos.request.impl.TransactionStatusDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.DataResponse;
import com.example.coffeebe.app.dtos.responses.TransactionResponse;
import com.example.coffeebe.domain.entities.business.Transaction;
import com.example.coffeebe.domain.services.impl.business.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/order")
public class TransactionController extends BaseController<Transaction, Long, TransactionResponse, TransactionFilterDto> {

	@Autowired
    TransactionService transactionService;

    public TransactionController() {
        super(TransactionResponse.class, TransactionFilterDto.class);
    }

    @GetMapping("/user")
    List<Transaction> getPageTransactionByUser(){
        return transactionService.findAllByUser();
    }

    @PostMapping("/{id}/status")
    TransactionResponse changeStatusTransaction(@PathVariable Long id, @Valid @RequestBody TransactionStatusDto transactionStatusDto){
        return transactionService.changeStatusTransaction(id, transactionStatusDto);
    }

    @GetMapping("/revenue")
    DataResponse getRevenue(@RequestParam("year") int year){
        return transactionService.getRevenueByYear(year);
    }

}
