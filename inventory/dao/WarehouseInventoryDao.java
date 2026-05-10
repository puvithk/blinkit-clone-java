package inventory.dao;

import inventory.model.WareHouseInventory;

import java.util.List;

public interface WarehouseInventoryDao {
    List<WareHouseInventory> findAllInventoryByWarehouseId(Integer warehouseId);

    List<WareHouseInventory> findWareHouseInventoryByWarehouseId(Integer warehouseId);
}
