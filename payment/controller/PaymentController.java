package payment.controller;

import common.response.CustomResponse;
import order.model.enums.PaymentMethod;
import payment.dto.PaymentResponse;
import payment.service.PaymentService;

public class PaymentController {
    // Object of payment service method
    private final PaymentService paymentService = new PaymentService();
    public CustomResponse<String> processPayment(Integer orderId, PaymentMethod selectedMethod) {
        return paymentService.processPayment(orderId , selectedMethod);
    }

    public CustomResponse<PaymentResponse> verifyPayment(String transactionId) {
        return paymentService.verifyPayment(transactionId);
    }
}
