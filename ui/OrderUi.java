package ui;

import cart.controller.CartController;
import cart.exception.CartNotFoundException;
import cart.exception.ProductOutOfStockException;
import cart.model.Cart;
import cart.model.CartItem;
import common.response.CustomResponse;
import order.controller.OrderController;
import order.dto.OrderPlacedResponse;
import order.dto.OrderResponse;
import order.model.Order;

import java.util.List;
import java.util.Scanner;

public class OrderUi {
    // Private cart controller to get all the product

    private final CartController cartController = new CartController();
     // Order controller
    private final OrderController orderController = new OrderController();
    // Payment Ui object
    private final PaymentUi paymentUi = new PaymentUi();
    // Scanner object
    private final Scanner scanner = new Scanner(System.in);
    public void placeOrderUi() {

        // Get all the products in the cart
        try {
            // get the data from the controller
            Cart cartProducts = cartController.getCartItems();
            for(CartItem cartItem : cartProducts.getCartItems()){
                System.out.printf("| %-27s | %-3d | %8.3f | %n", cartItem.getProduct().getProductTitle() , cartItem.getQuantity() , cartItem.getProduct().getPrice());

            }
            System.out.printf("%n| Total Amount : %f |%n" , cartProducts.getTotalAmount());

        }catch (CartNotFoundException cartNotFoundException){
            System.out.println("No items found in the cart ");
            System.out.println("------------------------------------------------------------------------------------");
            return;
        }
        while(true){
            System.out.println("Press 1 to proceed to payment.");
            System.out.println("Press 0 to cancel the order.");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice==1){
                placeOrder();
                break;
            }else if (choice==0){
                System.out.println("Canceling order ");
                return;
            }else{
                System.out.println("Enter an valid option : ");
                
            }
        }
 
        

    }

    private void placeOrder() {
        // Create a order
        CustomResponse<OrderPlacedResponse> response;
        try{
            response = orderController.placeOrderByUser();
        }catch (ProductOutOfStockException product){
            System.out.println("Product in cart is not present");
            return;
        }


        // Get the order details or Id
        if(response.isSuccess()){
            System.out.println("Order created successfully");

        }

        // Get the order id
        Integer orderId = response.getData().getId();

        // Button for confirmation for payment
        while(true){
            System.out.println("Press 1 for payment and confirmation");
            System.out.println("Press 0 to exit :");

            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice==1){
                // redirect to payment option with Order id
                // Once payment is complete place the order
                paymentUi.payment(orderId);
                break;
            }else if (choice==0){
                System.out.println("Exiting...");
                return;}
            else{
                System.out.println("Enter an valid option");
            }

        }



    }

    public void showOrders() {
        // Get the data from the controller
        CustomResponse<List<OrderResponse>> response = orderController.getAllOrderByUser();
        // Check is success and Print the object

        if(!response.isSuccess()){
            System.out.println("Problem in feteching : ");
            return;
        }
        List<OrderResponse> orderList = response.getData();

        if(orderList.isEmpty()){
            System.out.println("-----------------------------------------------\n\n No Order present \n\n-----------------------------------------------");
            return;
        }
            System.out.println("Order Id | Order Status | Total amount");
        for(OrderResponse orderResponse: orderList){

            System.out.println("-----------------------------------------------");
            System.out.printf("%-9d , %-14s , %-13f %n" , orderResponse.getOrderId() , orderResponse.getOrderStatus() , orderResponse.getTotalAmount());
            System.out.println("-----------------------------------------------");
        }


    }
}
