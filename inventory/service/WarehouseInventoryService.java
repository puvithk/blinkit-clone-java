package inventory.service;

import inventory.model.WareHouseInventory;
import order.model.Order;
import product.model.Product;

import java.util.List;

public interface WarehouseInventoryService {
    List<WareHouseInventory> getAllInventoryById(Integer warehouseId);

    boolean isProductAvailable(Integer warehouseId, Product product);

    void reduceProductQuantity(Order order);

    List<Product> findProductsByCategory(Integer warehouseId, int page , String category);
}
