package ui;

import cart.controller.CartController;
import cart.exception.CartNotFoundException;
import cart.model.Cart;
import cart.model.CartItem;
import product.model.Product;

import java.util.List;
import java.util.Scanner;

public class CartUi {
    //Cart controller to get the cart details
    private final CartController cartController = new CartController();
    // Create a scanner calss
    private final Scanner scanner = new Scanner(System.in);
    public void showCart() {
        // get the full carts details and show the total amount
        System.out.println("------------------------------------------------------------------------------------");
        try {
            // get the data from the controller
            Cart cartProducts = cartController.getCartItems();
            for(CartItem cartItem : cartProducts.getCartItems()){
                System.out.printf("| %-27s | %-3d | %8.3f | %n", cartItem.getProduct().getProductTitle() , cartItem.getQuantity() , cartItem.getProduct().getPrice());

            }
            System.out.printf("%n| Total Amount : %f |%n" , cartProducts.getTotalAmount());

        }catch (CartNotFoundException cartNotFoundException){
            System.out.println("No items found in the cart ");
            return;
        }
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Press 1 to PLACE ORDER : ");
        System.out.println("Press 0 to EXIT : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice==1){
            System.out.println("Order placed : ");

        }
        else{
            System.out.println("Exiting cart : ");

        }
    }
}
