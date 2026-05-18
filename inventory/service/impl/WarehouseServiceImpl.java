package inventory.service.impl;

import inventory.dao.WarehouseDao;
import inventory.dao.impl.WarehouseDaoImpl;
import inventory.exception.InvalidPincode;
import inventory.exception.WareHouseNotAvaliable;
import inventory.exception.WareHouseNotFoundException;
import inventory.model.WareHouse;
import inventory.service.WarehouseService;

public class WarehouseServiceImpl implements WarehouseService {
    // Get the dao object
    private final WarehouseDao warehouseDao = new WarehouseDaoImpl();
    @Override
    public Integer getNearestWarehouse(String pincode) {
        // Check weather pin code is valid
        if(!pincode.matches("^\\d{6}$")) throw new InvalidPincode("Pin code Invalid");

        Integer warehouseId = warehouseDao.findWarehouseByPincode(pincode);
        if(warehouseId == null){
            throw new WareHouseNotAvaliable("Ware house not available  in pin code ");
        }
        // Check weather the warehouse is available in the given pin code
        return warehouseId;
    }

    @Override
    public WareHouse getWarehouseById(Integer warehouseId) {
        WareHouse wareHouse = warehouseDao.findWarehouseById(warehouseId);
        if(wareHouse==null){
            throw new WareHouseNotFoundException("Warehouse is not available ");
        }
        return wareHouse;
    }
}
