package order.dao.impl;

import mockData.MockData;
import order.dao.OrderDao;
import order.model.Order;

import java.util.List;

public class InMemoryOrderDaoImpl implements OrderDao {
    private static List<Order> orderList = MockData.orderList;
    @Override
    public void createOrder(Order order) {
        Integer previousId = orderList.getLast().getId();
        order.setId(previousId + 1);
        orderList.addLast(order);

    }
}
