package payment.service;

import cart.service.CartService;
import common.response.CustomResponse;
import inventory.service.WarehouseInventoryService;
import inventory.service.WarehouseService;
import inventory.service.impl.WarehouseInventoryServiceImpl;
import inventory.service.impl.WarehouseServiceImpl;
import order.dto.OrderResponse;
import order.exception.PaymentNotFoundException;
import order.model.Order;
import order.model.enums.OrderStatus;
import order.model.enums.PaymentMethod;
import order.service.OrderService;
import payment.dao.PaymentDao;
import payment.dao.impl.InMemoryPaymentDaoImpl;
import payment.dto.PaymentResponse;
import payment.model.Payment;
import payment.model.enums.PaymentStatus;

import java.util.Random;
import java.util.UUID;

public class PaymentService {
    // random number generator
    private final Random random = new Random();
    // Get the Payment dao
    private final PaymentDao paymentDao= new InMemoryPaymentDaoImpl();
    // Get the Order controller
    private final OrderService orderService = new OrderService();
    // Get the cart service
    private final CartService cartService = new CartService();
    // Get the warehouse inventory
    private final WarehouseInventoryService warehouseInventoryService = new WarehouseInventoryServiceImpl();
    public CustomResponse<String> processPayment(Integer orderId, PaymentMethod selectedMethod) {
        //Check if order id is correct
        OrderResponse orderResponse = orderService.getOrderById(orderId).getData();

        // create a transaction id with payment details
        Payment  payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(orderResponse.getTotalAmount());
        payment.setPaymentMethod(selectedMethod);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setTransactionId(createTransactionalId(orderId , selectedMethod));

        // Update the database
        paymentDao.createPayment(payment);
        // return the details
        return new CustomResponse<>(
                true ,
                "Payment Transaction created" ,
                payment.getTransactionId()
        );
    }

    private String createTransactionalId(Integer orderId , PaymentMethod selectedMethod) {
    return UUID.randomUUID().toString();

    }

    public CustomResponse<PaymentResponse> verifyPayment(String transactionId) {
        // get the payment based on the transaction id
        Payment  payment = paymentDao.findPaymentByTransactionId(transactionId);
        if(payment==null){
            throw new PaymentNotFoundException("payment not found");
        }
        // Simulate the payment verification and updating
        paymentDao.updatePaymentStatus(payment , PaymentStatus.SUCCESS);
        // Update the database and confirm the payment
        // Update the order status
        orderService.updateOrderStatus(payment.getOrderId() , OrderStatus.CONFIRMED);

        // reduce the quantity of the orders in inventory
        // Get the order and send the Order object directly

        Order order = orderService.findOrderById(payment.getOrderId());

        warehouseInventoryService.reduceProductQuantity(order);


        // clear the cart
        cartService.clearCart();

        return new CustomResponse<>(
                true ,
                "Payment Successfully" ,
                new PaymentResponse(
                        payment.getOrderId() ,
                        payment.getAmount() ,
                        payment.getPaymentMethod(),
                        payment.getPaymentStatus(),
                        payment.getTransactionId(),
                        payment.getPaidAt()
                )
        );
    }
}
