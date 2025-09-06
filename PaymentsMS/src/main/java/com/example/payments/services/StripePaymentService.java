package com.example.payments.services;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Primary
public class StripePaymentService implements PaymentService{
    @Override
    public String generatePaymentLink(String orderId, Long amount) throws StripeException {
        //get key from stripe -> developers -> dashboard
        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");

        //Stripe.apiKey = "STRIPE_SECRET_KEY";
        PriceCreateParams priceCreateParams = PriceCreateParams.builder()
                .setCurrency("inr")
                .setUnitAmount((long)amount)
                .setProductData(
                        PriceCreateParams.ProductData.builder()
                                .setName("orderId: " +orderId)
                                .build()
                )
                .build();

        Price price = Price.create(priceCreateParams);

       
        PaymentLinkCreateParams paymentParams = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(price.getId())
                                .setQuantity(1L)
                                .build()
                )
                .setAfterCompletion(
                        PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(
                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://google.com")
                                                .build()
                                )
                                .build()
                )
                .build();

        return PaymentLink.create(paymentParams).getUrl();

    }
}
