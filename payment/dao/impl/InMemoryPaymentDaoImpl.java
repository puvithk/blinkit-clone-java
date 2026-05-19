package payment.dao.impl;

import mockData.MockData;
import payment.dao.PaymentDao;
import payment.model.Payment;
import payment.model.enums.PaymentStatus;

import java.util.List;

public class InMemoryPaymentDaoImpl implements PaymentDao {
    private final List<Payment> paymentList = MockData.paymentList;
    @Override
    public void createPayment(Payment payment) {
        Integer lastIndex = -1 ;
        if(!paymentList.isEmpty()){
             lastIndex = paymentList.getLast().getId();
        }

        payment.setId(lastIndex+1);
        paymentList.addLast(payment);
    }

    @Override
    public Payment findPaymentByTransactionId(String transactionId) {
        return paymentList.stream()
                .filter(payment -> payment.getTransactionId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        int index = paymentList.indexOf(payment);
        payment.setPaymentStatus(paymentStatus);
        paymentList.set(index , payment);
    }
}
