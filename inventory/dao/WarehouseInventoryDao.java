package inventory.dao;

import inventory.model.WareHouseInventory;
import product.model.Product;

import java.util.List;

public interface WarehouseInventoryDao {
    List<WareHouseInventory> findAllInventoryByWarehouseId(Integer warehouseId);

    List<WareHouseInventory> findWareHouseInventoryByWarehouseId(Integer warehouseId);

    void updateProductQuantity(Integer id, Product product, Integer quantity);

    List<Product> findProductsByCategory(Integer warehouseId, int page, String category);
}
