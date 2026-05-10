package product.controller;

import product.dto.CategorySubResponse;
import product.model.Product;
import product.service.ProductService;

import java.util.Collection;
import java.util.List;

public class ProductController {
    private final ProductService productService = new ProductService();
    public List<CategorySubResponse> getSubCategoriesByCategory() {
        return productService.getSubCategoriesByCategory();
    }

    public List<Product> getProductByCategory(int page , String category) {
        // Get the product based on the category
        return productService.getProductByCategory(page , category);
    }

    public Product getProductById(Integer Id) {
        // Get product details by id
        return productService.getProductById(Id);

    }
    // Get the product category
    // Get the items from cat use paging

}
