package inventory.model;

public class WareHouse {
    private Integer id ;
    private String name ;
    private String pincode;

    public WareHouse(Integer id, String name, String pincode) {
        this.id = id;
        this.name = name;
        this.pincode = pincode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
