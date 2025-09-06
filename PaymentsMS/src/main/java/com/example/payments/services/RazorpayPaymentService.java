package com.example.payments.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentService implements PaymentService{

    @Override
    public String generatePaymentLink(String orderId, Long amount) {
        return "";
    }
}
