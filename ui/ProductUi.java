package ui;

import cart.controller.CartController;
import cart.exception.ProductOutOfStockException;

import inventory.exception.WareHouseNotAvaliable;
import product.controller.ProductController;
import product.exception.ProductNotFound;
import product.model.Product;

import java.util.*;
import java.util.logging.Logger;

public class ProductUi {

    private final ProductController productController = new ProductController();
    // Cart controller
    private final CartController cartController = new CartController();
    // Logger
    private final Logger logger = Logger.getLogger(ProductUi.class.getName());
    // scanner
    private final Scanner scanner = new Scanner(System.in);
    public void productBasedOnCategory(String category){
        // get products based on the category
        int page = 0 ;
        // Input mapping for the product
        Map<Integer , Integer> inputMapping = new HashMap<>();
        List<Product> productList = new ArrayList<>(productController.getProductByCategory(page, category));
        while(true){
            if(productList.isEmpty()){
                System.out.println("No product found in the category");
            }
            for (int i = 0 ;i<productList.size() ;i++) {
                // Printing the product title

                System.out.println(i+1 + " " + productList.get(i).getProductTitle());

                // Mapping to the input index and the operation
                inputMapping.put(i+1 , productList.get(i).getId());
            }
            System.out.println(
                    "Press 0 to load more products, enter a product number to view details, or press any other key to exit."
            );
            int choice = scanner.nextInt();
            scanner.nextLine();
            // Checking to reload
            if(choice==0){
                // Increase the page size
                page++;
                // Variable to check weather new product is added
                int previousLength =  productList.size();
                // Add new items
                productList.addAll(productController.getProductByCategory(page , category));
                // Checking weather new items are added or not
                if(previousLength== productList.size()){
                    logger.info("No more items to load : ");

                }
                // Choice must be -1 top exit
            }else if (inputMapping.getOrDefault(choice , -1)== -1){

                return;
            }else{

                // If pressed any input mapping number get the product based on the id
                productBasedOnId(inputMapping.get(choice));
            }

        }
        // show the products


    }

    private void productBasedOnId(Integer integer) {
        try{
            Product product = productController.getProductById(integer);
            System.out.println("Product id " + product.toString());
            System.out.println("Press 1 to add to cart , Any other to exit :");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1){
                addProductToCart(product);
                }
        }catch (ProductNotFound productNotFound){
            System.out.println("Product not found : ");
        }



    }
    private void addProductToCart(Product product){
        try {
            cartController.addToMyCart(product);
            logger.info("Added Item to cart");

        } catch (ProductOutOfStockException outOfStockException) {
            System.out.println("Selected product is out of stock ");
        } catch (WareHouseNotAvaliable wareHouseNotAvaliable) {
            System.out.println("Currently not available in your location ");
        }
    }
}
