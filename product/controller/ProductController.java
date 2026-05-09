package product.controller;

import product.dto.CategorySubResponse;
import product.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService = new ProductService();
    public List<CategorySubResponse> getSubCategoriesByCategory() {
        return productService.getSubCategoriesByCategory();
    }
    // Get the product category
    // Get the items from cat use paging

}
