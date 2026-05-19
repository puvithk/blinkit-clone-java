package inventory.dao.impl;

import inventory.dao.WarehouseInventoryDao;
import inventory.exception.WareHouseNotAvaliable;
import inventory.model.WareHouseInventory;
import mockData.MockData;
import product.model.Product;

import java.util.List;

public class WarehouseInventoryDaoImpl  implements WarehouseInventoryDao {
    List<WareHouseInventory> wareHouseInventories = MockData.wareHouseInventories;

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

    @Override
    public void updateProductQuantity(Integer warehouseId, Product product, Integer quantity) {
        // Get the warehouse inventory and with the product and update the quantity
        WareHouseInventory wareHouseInventory = wareHouseInventories.stream().filter(
                wareHouseInventory1 ->
                        wareHouseInventory1.getWarehouse().getId().equals(warehouseId)
                && wareHouseInventory1.getProduct().equals(product))
                .findFirst()
                .orElse(null);

        if(wareHouseInventory==null){
            throw new WareHouseNotAvaliable("Warehouse or product not available ");
        }
        // Reduce the quantity
        wareHouseInventory.setQuantity(wareHouseInventory.getQuantity() - quantity);



    }

    @Override
    public List<Product> findProductsByCategory(Integer warehouseId, int page, String category) {
        int maxSize = 5 ;
        return wareHouseInventories.stream()
                .filter(wareHouseInventory ->
                        wareHouseInventory.getProduct().getCategory().getName().equals(category)
                                && wareHouseInventory.getQuantity() > 0)
                .map(WareHouseInventory::getProduct)
                .distinct()
                .skip((long) page * maxSize)
                .limit(maxSize)
                .toList();
    }
}
