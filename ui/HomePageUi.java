package ui;

import product.controller.ProductController;
import product.dto.CategorySubResponse;
import product.model.category.SubCategory;
import product.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomePageUi {
    private final ProductController productController = new ProductController();
    private final Scanner scanner = new Scanner(System.in);
    public void homePage(){
        // Display the available category and subcategory
        // Get all the available category and the subcategory
        int count =  1 ;
        // This variable is used to map the Count and the category
        Map<Integer , String> inputMapping =  new HashMap<>();
        // Get the category and Sub category list
        List<CategorySubResponse> categorySubResponseList = productController.getSubCategoriesByCategory();
        for(CategorySubResponse categorySubResponse : categorySubResponseList){
            System.out.println(categorySubResponse.getMainCategory().getName());
            for(SubCategory subCategory : categorySubResponse.getSubCategoryList()){
                inputMapping.put(count , subCategory.getName());
                System.out.println(count++ +". " + subCategory.getName());
            }
        }
        System.out.println("---------------------------------------------------");
        inputMapping.put(count , "HOME");
        inputMapping.put(count+1 , "ORDER_AGAIN");
        inputMapping.put(count+2 , "CATEGORIES");
        inputMapping.put(count+3 ,"EXIT");
        System.out.println("| "+count++ + "   HOME   |  "+count++ + " ORDER AGAIN    |   "+count++ + "  CATEGORIES    |   "+count++ + "  EXIT  | ");
        System.out.println("Enter any choice : ");
    }
}
