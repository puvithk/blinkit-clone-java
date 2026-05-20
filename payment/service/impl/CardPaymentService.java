package payment.service.impl;

import common.response.CustomResponse;
import payment.dto.CardPaymentRequest;
import payment.dto.PaymentBaseRequest;
import payment.service.PaymentService;

public class CardPaymentService extends PaymentService {

    @Override
    public CustomResponse<String> payment(PaymentBaseRequest paymentBaseRequest) {
        CardPaymentRequest cardPaymentRequest = (CardPaymentRequest) paymentBaseRequest;

        return new CustomResponse<>(
                true ,
                "Payment complete" ,
                cardPaymentRequest.getCardNumber()
        );
    }
}
