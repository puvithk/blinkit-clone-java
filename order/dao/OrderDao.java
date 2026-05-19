package order.dao;

import order.model.Order;
import order.model.enums.OrderStatus;

public interface OrderDao {

    void createOrder(Order order);

    Order findOrderById(Integer orderId);

    void updateOrderStatus(Order order, OrderStatus orderStatus);
}
