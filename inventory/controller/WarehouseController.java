package inventory.controller;

import inventory.service.WarehouseService;
import inventory.service.impl.WarehouseServiceImpl;

public class WarehouseController {
    // Warehouse service object
    private final WarehouseService warehouseService = new WarehouseServiceImpl();
    public Integer getNearestWarehouse(String pincode) {
        return warehouseService.getNearestWarehouse(pincode);
    }
    // here this controller is used to find the warehouse near me basedon the pincode

}
