package inventory.dao;

public interface WarehouseDao {
    Integer findWarehouseByPincode(String pincode);
}
