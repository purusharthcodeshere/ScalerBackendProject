package com.scaler.backendproject.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGatewayImplementation implements PaymentService{
    @Override
    public String makePayment(String orderId, Long amount) throws StripeException {
//        return "";
        // 1. Create PriceCreateParam Object
        //

        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderId).build()
                        )
                        .build();

        Price price = Price.create(params);

        //2. Creating Payment Link
        Stripe.apiKey = "sk_test_51R4LP0GdlDvgnUKHZ68zilHWkZxwMrj27XOF82jvNRrjEtC7hdBMha2I0a13YkcVcmZEEVcnKdlwlmKepnnKf3fg00eZjeTCHy";

        PaymentLinkCreateParams linkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        ).setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://probz.ai/")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(linkParams);

        return paymentLink.getId();
    }
}
