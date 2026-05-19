package payment.controller;

import common.response.CustomResponse;
import order.model.enums.PaymentMethod;
import payment.dto.PaymentResponse;
import payment.service.PaymentService;

public class PaymentController {
    // Object of payment service method
    private final PaymentService paymentService = new PaymentService();
    public CustomResponse<PaymentResponse> processPayment(Integer orderId, PaymentMethod selectedMethod) {
        return paymentService.processPayment(orderId , selectedMethod);
    }
}
