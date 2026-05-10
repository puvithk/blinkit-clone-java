package inventory.service.impl;

import inventory.dao.WarehouseInventoryDao;
import inventory.dao.impl.WarehouseInventoryDaoImpl;
import inventory.exception.WareHouseNotAvaliable;
import inventory.model.WareHouseInventory;
import inventory.service.WarehouseInventoryService;
import mockData.MockData;
import product.model.Product;

import java.util.List;

public class WarehouseInventoryServiceImpl implements WarehouseInventoryService {
    // DAO
  private WarehouseInventoryDao warehouseInventoryDao = new WarehouseInventoryDaoImpl();

    @Override
    public List<WareHouseInventory> getAllInventoryById(Integer warehouseId) {
        // return the list with warehouse id


        return warehouseInventoryDao.findAllInventoryByWarehouseId(warehouseId);

    }

    @Override
    public boolean isProductAvailable(Integer warehouseId, Product product) {
        // Get the warehouse inventories
        List<WareHouseInventory> wareHouseInventories = warehouseInventoryDao.findWareHouseInventoryByWarehouseId(warehouseId);

        if (wareHouseInventories.isEmpty()) {
            throw new WareHouseNotAvaliable("Warehouse not available ");
        }
        return wareHouseInventories.stream()
                .anyMatch(wareHouseInventory ->
                        wareHouseInventory.getProduct().equals(product)
                                && wareHouseInventory.getQuantity() > 0
                );
    }
}
