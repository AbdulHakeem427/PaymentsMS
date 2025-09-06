package com.example.payments.controllers;


import com.example.payments.dtos.PaymentRequestDTO;
import com.example.payments.services.PaymentService;
import com.example.payments.services.StripePaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")//because we are creating a new entry in PG db
    public String createPaymentLink(@RequestBody PaymentRequestDTO paymentRequestDTO) throws StripeException {
           String url = paymentService.generatePaymentLink(paymentRequestDTO.getOrderID(), paymentRequestDTO.getAmount());
           return url;
    }
    @PostMapping("/webhooks")//we update the db
    public String handleWebhook(){
           System.out.println("request for webhook received");
           System.out.println("updating database.... ");
           return "webhood completed";
    }
    @PostMapping("/webhooks/successful")//we update the db
    public String handleWebhookSuccessful(){
        System.out.println("payment successful");
        System.out.println("updating database with successful ");
        return "webhood completed";
    }
}
