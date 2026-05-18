package inventory.service;

import inventory.model.WareHouse;

public interface WarehouseService {
    Integer getNearestWarehouse(String pincode);

    WareHouse getWarehouseById(Integer warehouseId);
}
