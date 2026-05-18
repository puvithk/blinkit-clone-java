package order.controller;

import common.response.CustomResponse;
import order.dto.OrderPlacedResponse;
import order.service.OrderService;

public class OrderController {
    // Get order service bean
    private final OrderService orderService = new OrderService();
    // Controller to place a orders
    public CustomResponse<OrderPlacedResponse> placeOrderByUser(){
        return orderService.placeOrderByUser();
    }
}
