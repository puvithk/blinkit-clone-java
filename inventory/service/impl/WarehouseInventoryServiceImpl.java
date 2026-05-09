package inventory.service.impl;

import inventory.model.WareHouseInventory;
import inventory.service.WarehouseInventoryService;
import mockData.MockData;

import java.util.List;

public class WarehouseInventoryServiceImpl implements WarehouseInventoryService {
    List<WareHouseInventory> wareHouseInventories = MockData.getObject().wareHouseInventories;

    @Override
    public List<WareHouseInventory> getAllInventoryById(Integer warehouseId) {
        // return the list with warehouse id
        return wareHouseInventories.stream()
                .filter(wareHouseInventory ->
                        wareHouseInventory.getWarehouse().getId().equals( warehouseId))
                .toList();
    }
}
