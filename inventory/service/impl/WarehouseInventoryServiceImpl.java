package inventory.service.impl;

import common.security.SecurityContext;
import inventory.dao.WarehouseInventoryDao;
import inventory.dao.impl.WarehouseInventoryDaoImpl;
import inventory.exception.WareHouseNotAvaliable;
import inventory.model.WareHouseInventory;
import inventory.service.WarehouseInventoryService;
import mockData.MockData;
import order.model.Order;
import order.model.OrderItem;
import order.service.OrderService;
import product.model.Product;

import java.util.List;

public class WarehouseInventoryServiceImpl implements WarehouseInventoryService {
    // DAO
  private final WarehouseInventoryDao warehouseInventoryDao = new WarehouseInventoryDaoImpl();

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
                        wareHouseInventory.getProduct().getId().equals(product.getId())
                                && wareHouseInventory.getQuantity() > 0
                );
    }

    @Override
    public void reduceProductQuantity(Order order) {


        // check the cartItems
        for(OrderItem orderItem : order.getOrderItems()){
            // Get the warehouse info
            // Get the product from the order items
            // get the quantity which is requested
            // Using these update the warehouseInventoryDao
            warehouseInventoryDao.updateProductQuantity(
                    order.getWareHouse().getId(),
                    orderItem.getProduct() ,
                    orderItem.getQuantity()
            );
        }
        // Update in the database using DAO
    }
}
