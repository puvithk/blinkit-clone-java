package inventory.service;

import inventory.model.WareHouseInventory;

import java.util.List;

public interface WarehouseInventoryService {
    List<WareHouseInventory> getAllInventoryById(Integer warehouseId);
}
