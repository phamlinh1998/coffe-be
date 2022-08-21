package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Transaction;
import com.example.coffeebe.domain.entities.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TransactionStatusDto implements DTO<Transaction> {


    @NotNull(message = "status not null")
    private String status;

    @AssertTrue(message = "status is incorrect")
    protected boolean isValidStatus() {
        return status.equals(TransactionStatus.WAIT_FOR_APPROVE.toString())
                || status.equals(TransactionStatus.APPROVED.toString())
                || status.equals(TransactionStatus.TRANSPORT.toString())
                || status.equals(TransactionStatus.CANCEL.toString())
                || status.equals(TransactionStatus.RECEIVED.toString())
                || status.equals(TransactionStatus.SUCCESSFUL_TRANSPORT.toString());
    }

}
