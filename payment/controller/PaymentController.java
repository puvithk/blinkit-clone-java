package payment.controller;

import common.response.CustomResponse;
import order.model.enums.PaymentMethod;
import payment.dto.PaymentResponse;
import payment.paymentBean.PaymentBeanFactory;
import payment.service.PaymentService;

public class PaymentController {
    // Object of payment service method
    private PaymentService paymentService;
    public CustomResponse<String> processPayment(Integer orderId, PaymentMethod selectedMethod) {
        paymentService = PaymentBeanFactory.getPaymentBeanByMethod(selectedMethod);
        return paymentService.processPayment(orderId , selectedMethod);
    }

    public CustomResponse<PaymentResponse> verifyPayment(String transactionId ,PaymentMethod selectedMethod) {
        paymentService = PaymentBeanFactory.getPaymentBeanByMethod(selectedMethod);
        return paymentService.verifyPayment(transactionId);
    }
}
