package order.model;

import inventory.model.WareHouse;
import order.model.enums.OrderStatus;
import order.model.enums.PaymentMethod;
import user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

        private Integer id;

        private User user;


    private WareHouse  wareHouse;

        private List<OrderItem> orderItems;

        private Double totalAmount;

        private OrderStatus orderStatus;

        private PaymentMethod paymentMethod;

        private String deliveryAddress;

        private Integer estimatedDeliveryTime;

        private LocalDateTime orderedAt;


    public Order(Integer id, User user, List<OrderItem> orderItems, Double totalAmount, OrderStatus orderStatus, PaymentMethod paymentMethod, String deliveryAddress, Integer estimatedDeliveryTime, LocalDateTime orderedAt) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.orderedAt = orderedAt;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentMethod getPayment() {
        return paymentMethod;
    }

    public void setPayment(PaymentMethod payment) {
        this.paymentMethod = payment;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


}
