package order.controller;

import common.response.CustomResponse;
import order.dto.OrderPlacedResponse;
import order.dto.OrderResponse;
import order.service.OrderService;

import java.util.List;

public class OrderController {
    // Get order service bean
    private final OrderService orderService = new OrderService();
    // Controller to place a orders
    public CustomResponse<OrderPlacedResponse> placeOrderByUser(){
        return orderService.placeOrderByUser();
    }

    public CustomResponse<OrderResponse> getOrderById(Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    public CustomResponse<List<OrderResponse>> getAllOrderByUser(){
        return orderService.getAllOrderByUser();
    }
}
