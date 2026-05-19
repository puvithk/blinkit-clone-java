package ui;

import common.response.CustomResponse;
import order.model.enums.PaymentMethod;
import payment.controller.PaymentController;
import payment.dto.PaymentResponse;

import java.util.Scanner;

public class PaymentUi {
    // Scanner method
    private final Scanner scanner = new Scanner(System.in);

    // Payment controller
    private final PaymentController paymentController = new PaymentController();
    public void payment(Integer orderId) {
        // Show all payment options
        System.out.println("Select payment option for Order Id : " + orderId);
        PaymentMethod[] paymentMethods = PaymentMethod.values();

        for (int i = 0; i < paymentMethods.length; i++) {
            System.out.println((i + 1) + ". " + paymentMethods[i]);
        }

        System.out.println("Select a payment method:");
        int choice = scanner.nextInt();
        if(choice < 1 || choice > paymentMethods.length){
            System.out.println("Invalid payment method selected.");
            return;
        }

        PaymentMethod selectedMethod = paymentMethods[choice - 1];

        System.out.println("Selected Payment Method: " + selectedMethod);
        // Select the payment method
        // Call the payment controller with the payment method and the orderId
        processPayment(orderId , selectedMethod);

        // If COD just update the database
        // If other call the confirm method
        // Based on the payment method update the ddata

    }
    public void processPayment(Integer orderId , PaymentMethod selectedMethod){
        try {
            CustomResponse<PaymentResponse> response =  paymentController.processPayment(orderId , selectedMethod);
            if(response.isSuccess()){

                // This method shows other method for payment
                // In this just simulate the payment and confirm the order
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
