package order.dto;

import order.model.enums.OrderStatus;
import order.model.enums.PaymentMethod;

public class OrderPlacedResponse {
    private Integer id ;
    // Order amount
    private Double totalAmount;
    // Maximum time required
    private Integer estimatedDeliveryTime;
    // Current delivery location
    private String deliveryAddress;
    // Status
    private OrderStatus orderStatus;

    private PaymentMethod paymentStatus;

    public OrderPlacedResponse(Integer id, Double totalAmount, Integer estimatedDeliveryTime, String deliveryAddress, OrderStatus orderStatus, PaymentMethod paymentStatus) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentMethod getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentMethod paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
