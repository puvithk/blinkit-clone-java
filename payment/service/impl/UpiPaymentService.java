package payment.service.impl;

import common.response.CustomResponse;
import payment.dto.PaymentBaseRequest;
import payment.dto.UpiPaymentRequest;
import payment.service.PaymentService;

public class UpiPaymentService extends PaymentService {

    @Override
    public CustomResponse<String> payment(PaymentBaseRequest paymentBaseRequest) {
        UpiPaymentRequest upiPaymentRequest = (UpiPaymentRequest) paymentBaseRequest;
        // Process the upi details return thet tarncation id
        return new CustomResponse<>(
                true ,
                "Payment complete" ,
                upiPaymentRequest.getUpiId()
        );
    }
}
