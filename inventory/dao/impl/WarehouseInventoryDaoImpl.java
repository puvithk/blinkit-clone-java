package inventory.dao.impl;

import inventory.dao.WarehouseInventoryDao;
import inventory.model.WareHouseInventory;
import mockData.MockData;

import java.util.List;

public class WarehouseInventoryDaoImpl  implements WarehouseInventoryDao {
    List<WareHouseInventory> wareHouseInventories = MockData.getObject().wareHouseInventories;

    @Override
    public List<WareHouseInventory> findAllInventoryByWarehouseId(Integer warehouseId) {
        return wareHouseInventories.stream()
                .filter(wareHouseInventory ->
                        wareHouseInventory.getWarehouse().getId().equals( warehouseId))
                .toList();
    }

    @Override
    public List<WareHouseInventory> findWareHouseInventoryByWarehouseId(Integer warehouseId) {
        return wareHouseInventories.stream()
                .filter(wareHouseInventory -> wareHouseInventory.getWarehouse().getId().equals(warehouseId))
                .toList();
    }
}
