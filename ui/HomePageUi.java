package ui;

import product.controller.ProductController;
import product.dto.CategorySubResponse;
import product.model.category.SubCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomePageUi {
    private final ProductController productController = new ProductController();
    // Product ui based on the category
    private final ProductUi productUi = new ProductUi();
    // Order ui object
    private final OrderUi orderUi = new OrderUi();
    // Cart ui object
    private final CartUi cartUi = new CartUi();
    // Scanner for input
    private final Scanner scanner = new Scanner(System.in);
    public void homePage(){
        while(true){
            boolean isExit = homeUi();
            if(isExit){
                return;
            }
        }

    }
    public boolean homeUi(){
        // Display the available category and subcategory
        // Get all the available category and the subcategory
        int inputCount =  1 ; // Track the Number of input options
        // This variable is used to map the Count and the category
        Map<Integer , String> inputMapping =  new HashMap<>();
        // Get the category and Sub category list
        List<CategorySubResponse> categorySubResponseList = productController.getSubCategoriesByCategory();
        // list out all the category and subcategory
        for(CategorySubResponse categorySubResponse : categorySubResponseList){
            // Print the name of the Category
            System.out.println(categorySubResponse.getMainCategory().getName());
            // Print all the subcategorys
            for(SubCategory subCategory : categorySubResponse.getSubCategoryList()){
                inputMapping.put(inputCount , subCategory.getName());
                System.out.println(inputCount++ +". " + subCategory.getName());
            }
        }
        System.out.println("---------------------------------------------------");
        int totalCategory = inputCount;
        inputMapping.put(inputCount , "HOME");
        inputMapping.put(inputCount+1 , "ORDER_AGAIN");
        inputMapping.put(inputCount+2 , "CATEGORIES");
        inputMapping.put(inputCount+3 , "CART");
        inputMapping.put(inputCount+4 ,"EXIT");

        System.out.println("| "+ inputCount++ + "   HOME   |  "+ inputCount++ + " ORDER AGAIN    |   "+inputCount++ + "  CATEGORIES    |   "+inputCount++ + " CART    |  "+  inputCount++ + "  EXIT  | ");
        System.out.println("Enter any choice : ");
        int choice = scanner.nextInt();

        if(choice > 0  && choice <= totalCategory ){
            // Product based on the category
            productUi.productBasedOnCategory( inputMapping.get(choice));
        }
        if(inputMapping.get(choice).equals("EXIT")){
            return true;
        }
        if(inputMapping.get(choice).equals("CART")){
            cartUi.showCart();
        }
        if(inputMapping.get(choice).equals("ORDER_AGAIN")){
            orderUi.showOrders();
        }
        return false;
    }
}
