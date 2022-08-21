package com.example.coffeebe.app.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private List<OrderResponse> orders;

    @JsonProperty("user_phone")
    private String userPhone;

    @JsonProperty("user_email")
    private String userEmail;

    private String address;

    private String status;

    private Long amount;

    private UserResponse user;

    @JsonProperty("payment_info")
    private String paymentInfo;

    private String payment;

    private Long id;

    @JsonFormat(pattern = "dd-MM-YYYY HH:ss")
    private Timestamp created_at;

}
