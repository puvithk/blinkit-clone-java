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

        List<SubCategory> subCategoryList = productDao.findAllSubCategoryList();

        Map<MainCategory, List<SubCategory>> categorySubResponseList =
                subCategoryList.stream()
                        .collect(Collectors.groupingBy(SubCategory::getMainCategory));

        return categorySubResponseList.entrySet()
                .stream()
                .map(e -> new CategorySubResponse(
                        e.getKey() , e.getValue()
                ))
                .toList();
    }

    public List<Product> getProductByCategory(int page , String category) {
        Integer warehouseId = SecurityContext.getContext().getWarehouseId();
        int maxSize = 5 ;
        List<WareHouseInventory> wareHouseInventoryList = warehouseInventoryService.getAllInventoryById(warehouseId);
        return wareHouseInventoryList.stream()
                .filter(wareHouseInventory ->wareHouseInventory.getProduct().getCategory().getName().equals(category)
                        && wareHouseInventory.getQuantity() > 0)
                .map(WareHouseInventory::getProduct)
                .skip((long) page * maxSize)
                .limit(maxSize)
                .toList();
    }

    public Product getProductById(Integer id) {
        Product product =productDao.findProductById(id);
        if(product==null){
            throw new ProductNotFound("Product not found");
        }
        return product;
    }
}
