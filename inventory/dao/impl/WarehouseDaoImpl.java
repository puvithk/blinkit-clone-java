package inventory.dao.impl;

import inventory.dao.WarehouseDao;
import inventory.model.WareHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseDaoImpl implements WarehouseDao {
    private List<WareHouse> wareHouseList = new ArrayList<>(
            List.of(
                     new WareHouse(1,"Anvish Pvt" , "575022") ,
                    new WareHouse(2,"Manuvith Pvt" , "575056")
            )
    );


    @Override
    public Integer findWarehouseByPincode(String pincode) {
        return wareHouseList.stream()
                .filter(wareHouse -> wareHouse.getPincode().equals(pincode))
                .map(WareHouse::getId)
                .findFirst().orElse(null);
    }
}
