package inventory.dao;

import inventory.model.WareHouse;

public interface WarehouseDao {
    Integer findWarehouseByPincode(String pincode);

    WareHouse findWarehouseById(Integer warehouseId);
}
