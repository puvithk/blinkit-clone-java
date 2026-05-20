package payment.paymentBean;

import order.model.enums.PaymentMethod;
import payment.service.PaymentService;
import payment.service.impl.CardPaymentService;
import payment.service.impl.UpiPaymentService;

public class PaymentBeanFactory {
    public static PaymentService getPaymentBeanByMethod(PaymentMethod paymentMethod){
        

        return switch (paymentMethod) {

            case UPI ->
                    new UpiPaymentService();

            case CARD, COD , WALLET->
                    new CardPaymentService();

        };
    }
}
