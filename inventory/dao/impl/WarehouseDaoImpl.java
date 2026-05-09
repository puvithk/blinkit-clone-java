package inventory.dao.impl;

import inventory.dao.WarehouseDao;
import inventory.model.WareHouse;
import mockData.MockData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseDaoImpl implements WarehouseDao {
    private final List<WareHouse> wareHouseList = MockData.getObject().wareHouseList;


    @Override
    public Integer findWarehouseByPincode(String pincode) {
        return wareHouseList.stream()
                .filter(wareHouse -> wareHouse.getPincode().equals(pincode))
                .map(WareHouse::getId)
                .findFirst().orElse(null);
    }
}
