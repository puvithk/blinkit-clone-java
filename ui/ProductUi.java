package ui;

import product.controller.ProductController;
import product.model.Product;

import java.util.*;

public class ProductUi {

    private final ProductController productController = new ProductController();
    // scanner
    private final Scanner scanner = new Scanner(System.in);
    public void productBasedOnCategory(String category){
        // get products based on the category
        int page = 0 ;
        List<Product> productList = new ArrayList<>();
        // Input mapping for the product
        Map<Integer , Integer> inputMapping = new HashMap<>();
        productList.addAll(productController.getProductByCategory(page , category));
        while(true){

            for (int i = 0 ;i<productList.size() ;i++) {
                System.out.println(i+1 + " " + productList.get(i).getProductTitle());
                inputMapping.put(i+1 , productList.get(i).getId());
            }
            System.out.println(
                    "Press 0 to load more products, enter a product number to view details, or press any other key to exit."
            );
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if(choice==0){
                page++;
                productList.addAll(productController.getProductByCategory(page , category));
            }else if (inputMapping.getOrDefault(choice , -1)== -1){
                return;
            }else{
                productBasedOnId(inputMapping.get(choice));
            }

        }
        // show the products


    }

    private void productBasedOnId(Integer integer) {
        System.out.println("Product id " + integer);
    }
}
