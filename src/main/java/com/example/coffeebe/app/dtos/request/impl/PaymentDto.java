package com.example.coffeebe.app.dtos.request.impl;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDto {

    private String id;

    private String state;

    private List<TransactionDto> transactions;

    @Data
    public static class TransactionDto {
        private AmountDto amount;

    }

    @Data
    public static class AmountDto {

        private String total;

    }

}
