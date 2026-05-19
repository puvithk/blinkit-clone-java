package product.service;

import common.security.SecurityContext;
import inventory.model.WareHouseInventory;
import inventory.service.WarehouseService;
import inventory.service.impl.WarehouseInventoryServiceImpl;
import inventory.service.impl.WarehouseServiceImpl;
import product.dao.ProductDao;
import product.dao.impl.InMemoryProductDaoImpl;
import product.dto.CategorySubResponse;
import product.exception.ProductNotFound;
import product.model.Product;
import product.model.category.MainCategory;
import product.model.category.SubCategory;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductDao productDao = new InMemoryProductDaoImpl();

    // Get the object of warehouse inventry service
    private final WarehouseInventoryServiceImpl  warehouseInventoryService = new WarehouseInventoryServiceImpl();
    public List<CategorySubResponse> getSubCategoriesByCategory() {
        // Get the subcategories
        List<SubCategory> subCategoryList = productDao.findAllSubCategoryList();
        // Collect based on the main category
        Map<MainCategory, List<SubCategory>> categorySubResponseList =
                subCategoryList.stream()
                        .collect(Collectors.groupingBy(SubCategory::getMainCategory));
        // Create a entry set to map the category and subcategory
        // MAIN CATEGORY -> (subcategory1 , subcategory2 )
        return categorySubResponseList.entrySet()
                .stream()
                .map(e -> new CategorySubResponse(
                        e.getKey() , e.getValue()
                ))
                .toList();
    }

    public List<Product> getProductByCategory(int page , String category) {
        // Get the warehouse details
        Integer warehouseId = SecurityContext.getContext().getWarehouseId();


        return  warehouseInventoryService.findProductsByCategory(warehouseId ,page , category);

    }

    public Product getProductById(Integer id) {
        Product product =productDao.findProductById(id);
        if(product==null){
            throw new ProductNotFound("Product not found");
        }
        return product;
    }
}
