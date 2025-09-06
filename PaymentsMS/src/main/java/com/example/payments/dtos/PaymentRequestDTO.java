package com.example.payments.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    String orderID;
    Long amount;
}
