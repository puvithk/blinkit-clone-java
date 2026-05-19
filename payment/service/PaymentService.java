package payment.service;

import common.response.CustomResponse;
import order.model.enums.PaymentMethod;
import payment.dto.PaymentResponse;

public class PaymentService {
    public CustomResponse<PaymentResponse> processPayment(Integer orderId, PaymentMethod selectedMethod) {
    }
}
