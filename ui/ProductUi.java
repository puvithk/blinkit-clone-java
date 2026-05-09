package ui;

import product.controller.ProductController;
import product.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductUi {

    private final ProductController productController = new ProductController();
    public void productBasedOnCategory(String category){
        // get products based on the category
        int page = 0 ;
        List<Product> productList = new ArrayList<>();
        while(true){
            productList.addAll(productController.getProductByCategory(page , category));
            productList.forEach(
                    product -> System.out.println(product.getProductTitle())
            );
            System.out.println("Press 0 to load more : ");
            break;

        }
        // show the products


    }
}
