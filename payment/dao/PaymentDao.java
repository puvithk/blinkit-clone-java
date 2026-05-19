package payment.dao;

import payment.model.Payment;
import payment.model.enums.PaymentStatus;

public interface PaymentDao {
    void createPayment(Payment payment);

    Payment findPaymentByTransactionId(String transactionId);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
