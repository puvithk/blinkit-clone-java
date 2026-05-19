package order.dao.impl;

import mockData.MockData;
import order.dao.OrderDao;
import order.model.Order;
import order.model.enums.OrderStatus;

import java.util.List;

public class InMemoryOrderDaoImpl implements OrderDao {
    private static final List<Order> orderList = MockData.orderList;
    @Override
    public void createOrder(Order order) {
        Integer previousId = -1;
        if(!orderList.isEmpty()){
            previousId = orderList.getLast().getId();
        }

        order.setId(previousId + 1);
        orderList.addLast(order);

    }

    @Override
    public Order findOrderById(Integer orderId) {
        return orderList.stream().filter(
                order -> order.getId().equals(orderId)
        ).findFirst().orElse(null);
    }

    @Override
    public void updateOrderStatus(Order order, OrderStatus orderStatus) {
        int index = orderList.indexOf(order);
        order.setOrderStatus(orderStatus);
        orderList.set(index , order);
    }

    @Override
    public List<Order> findAllOrderByUserId(Integer userId) {
        return orderList
                .stream()
                .filter(order -> order.getUser().getId().equals(userId))
                .toList();
    }
}
