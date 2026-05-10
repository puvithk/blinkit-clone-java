package inventory.service;

import inventory.model.WareHouseInventory;
import product.model.Product;

import java.util.List;

public interface WarehouseInventoryService {
    List<WareHouseInventory> getAllInventoryById(Integer warehouseId);

    boolean isProductAvailable(Integer warehouseId, Product product);
}
